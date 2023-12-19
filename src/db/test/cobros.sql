/*Para los clientes mensuales*/
select * from mensualidades;
select * from pagos;
update pagos set Fec_Pago = "2023-10-10" where ID_Pago = 1; /*Solo para comprobar que funciona la ventana cobros*/

/*Para los clientes semanales*/
select * from clientesemanal;
select * from pagosemanales;
select * from pagos;
update pagos set Fec_Pago = "2023-10-10" where ID_Pago = 2; /*Para verificar que funciona la ventana de cobros*/

/*Para los alumnos de Kick Boxing*/
select * from inscripcionkick;
select * from pagokick;
select * from pagos;
update pagokick set FecVig_PagoK = "2023-10-10" where ID_PagoK = 1; /*Para verificar el funcionamiento de la ventana cobros*/
