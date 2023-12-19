SELECT 
    IK.ID_Kick AS 'ID del alumno',
    CONCAT(IK.Nom_Kick, ' ', IK.ApPat_Kick, ' ', IK.ApMat_Kick) AS 'Nombre completo',
    Last_Payments.Last_Pago AS 'Último pago'
FROM 
    InscripcionKick IK
LEFT JOIN (
    SELECT ID_Kick, MAX(FecVig_PagoK) AS Last_Pago
    FROM PagoKick
    GROUP BY ID_Kick
) AS Last_Payments ON IK.ID_Kick = Last_Payments.ID_Kick
WHERE 
    IK.Est_Kick = 'Activo'
    AND Last_Payments.Last_Pago < CURDATE() -- Mostrar solo pagos anteriores a la fecha actual
GROUP BY 
    IK.ID_Kick, IK.Nom_Kick, IK.ApPat_Kick, IK.ApMat_Kick;
