SELECT v.ID_Venta, v.ID_Pago, p.Form_Pago, v.Hor_Venta, d.ID_Prod, 
i.T_Prod, i.Nom_Prod, i.Pre_Prod, d.Cantidad_V, d.Importe_V
FROM Ventas v
INNER JOIN DetalleDeLaVenta d ON v.ID_Venta = d.ID_Venta
INNER JOIN Pagos p on v.ID_Pago = p.ID_Pago
INNER JOIN Inventario i on i.ID_Prod = d.ID_Prod;

