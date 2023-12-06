SELECT 
    COALESCE(C.ID_Cli, CS.ID_Sem) AS ID_Cliente,
    CONCAT(COALESCE(C.Nom_Cli, CS.Nom_Sem), ' ', COALESCE(C.ApPat_Cli, CS.ApPat_Sem), ' ', COALESCE(C.ApMat_Cli, CS.ApMat_Sem)) AS Nombre_Completo,
    COALESCE(PM.Desc_Pago, 'Pago semanal') AS Descripcion_Pago,
    COALESCE(LP.Fec_Pago, 'N/A') AS Ultimo_Pago
FROM 
    (
    SELECT 
        ID_Cli, 
        ID_Pago,
        'Pago mensual' AS Desc_Pago
    FROM 
        Mensualidades
    UNION ALL
    SELECT 
        ID_Sem AS ID_Cli, 
        ID_Pago,
        'Pago semanal' AS Desc_Pago
    FROM 
        ClienteSemanal
    UNION ALL
    SELECT 
        ID_CliSem AS ID_Cli,
        ID_Pago,
        'Pago semanal' AS Desc_Pago
    FROM 
        pagosemanales
    ) AS PM
LEFT JOIN 
    Clientes C ON PM.ID_Cli = C.ID_Cli
LEFT JOIN 
    ClienteSemanal CS ON PM.ID_Cli = CS.ID_Sem
LEFT JOIN 
    Pagos P ON PM.ID_Pago = P.ID_Pago
LEFT JOIN (
    SELECT 
        ID_Cli,
        MAX(Fec_Pago) AS Fec_Pago
    FROM 
        (
        SELECT 
            ID_Cli,
            ID_Pago
        FROM 
            Mensualidades
        UNION ALL
        SELECT 
            ID_Sem AS ID_Cli,
            ID_Pago
        FROM 
            ClienteSemanal
        UNION ALL
        SELECT 
            ID_CliSem AS ID_Cli,
            ID_Pago
        FROM 
            pagosemanales
        ) AS PM
    LEFT JOIN Pagos ON PM.ID_Pago = Pagos.ID_Pago
    GROUP BY 
        ID_Cli
    ) AS LP ON (C.ID_Cli = LP.ID_Cli OR CS.ID_Sem = LP.ID_Cli)
WHERE 
    (
    (PM.Desc_Pago = 'Pago mensual' OR PM.Desc_Pago = 'Pago semanal') AND 
    COALESCE(LP.Fec_Pago, '1900-01-01') <
        CASE
            WHEN PM.Desc_Pago = 'Pago mensual' THEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH)
            WHEN PM.Desc_Pago = 'Pago semanal' THEN DATE_SUB(CURDATE(), INTERVAL 1 WEEK)
            ELSE CURDATE()
        END
    )
    AND (C.Est_Cli = 'Activo' OR CS.Est_Sem = 'Activo') -- Condición para clientes activos
GROUP BY 
    ID_Cliente
ORDER BY 
    ID_Cliente;
