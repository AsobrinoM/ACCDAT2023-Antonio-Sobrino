package com.mycompany.basededatos;

import java.sql.*;
import java.util.Properties;

public class BASEDEDATOS {

    static String driver;
    static Connection conexion;

    public static void main(String[] args) {
        try {
            establecerConexion();
            //insertarExecuteUpdate();
            //insertarExecute();
            //insertarPrepared();
            //crearTabla();
            obtenerEmpleadoMaxSalario();
            //FILE2SELECT(10);
            // añadirCampo();

            // consultaExecute();
            // consultaPrepared();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    private static void establecerConexion() throws ClassNotFoundException, SQLException {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String urlconnection = "jdbc:oracle:thin:@localhost:1521/ORCL18";

        Properties propiedades = new Properties();
        propiedades.setProperty("user", "C##USSOP");
        propiedades.setProperty("password", "ala");
        Class.forName(driver);
        conexion = DriverManager.getConnection(urlconnection, propiedades);
        System.out.println("Conexion creada con ussop");
    }
  public static void obtenerEmpleadoMaxSalario() throws SQLException {
    String sql = "SELECT E.APELLIDO, E.SALARIO, D.DNOMBRE " +
                 "FROM EMPLEADOS E " +
                 "JOIN DEPARTAMENTOS D ON E.DEPT_NO = D.DEPT_NO " +
                 "WHERE E.SALARIO = (SELECT MAX(SALARIO) FROM EMPLEADOS)";

    try (Statement statement = conexion.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {

        while (resultSet.next()) {
            String apellido = resultSet.getString("APELLIDO");
            double salario = resultSet.getDouble("SALARIO");
            String nombreDepartamento = resultSet.getString("DNOMBRE");

            System.out.println("Empleado con máximo salario:");
            System.out.println("Apellido: " + apellido);
            System.out.println("Salario: " + salario);
            System.out.println("Departamento: " + nombreDepartamento);
        }
    }
}
    private static void consultaSencilla() throws SQLException {
        String sql = "Select * from departamentos";
        Statement sentencia = conexion.createStatement();
        ResultSet resul = sentencia.executeQuery(sql);
        while (resul.next()) {
            System.out.print("Número de departamento: " + resul.getInt(1));
            System.out.print("  Nombre de departamento: " + resul.getString(2));
            System.out.println("  Localizacion: " + resul.getString(3));
        }
        resul.close();
        sentencia.close();

        System.out.println("Se ha establecido la conexion al usuario: " + conexion.getSchema());
    }

    public static void FILE2SELECT(int deptNo) throws SQLException {
        String sql = "SELECT APELLIDO,OFICIO, SALARIO FROM EMPLEADOS WHERE DEPT_NO = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, deptNo);
            try (ResultSet resul = sentencia.executeQuery()) {
                System.out.println("Apellidos, oficio y salario de los empleados en el departamento " + deptNo + ":");
                while (resul.next()) {
                    String apellido = resul.getString("APELLIDO");
                    String oficio = resul.getString("OFICIO");
                    double salario = resul.getDouble("SALARIO");
                    System.out.println("Apellido: " + apellido + ", Oficio: " + oficio + ", Salario: " + salario);
                }
            }
        }
    }

    private static void crearTabla() throws SQLException {
        // SQL para crear la tabla
        String sql = "CREATE TABLE VENTAS (" +
                     "IDVENTA NUMBER PRIMARY KEY," +
                     "FECHAVENTA DATE NOT NULL," +
                     "IDCLIENTE NUMBER," +
                     "IDPRODUCTO NUMBER," +
                     "CANTIDAD NUMBER," +
                     "FOREIGN KEY (IDCLIENTE) REFERENCES CLIENTES(ID)," +
                     "FOREIGN KEY (IDPRODUCTO) REFERENCES PRODUCTOS(ID)" +
                     ")";

        // Creamos el statement
        Statement statement = conexion.createStatement();

        // Ejecutamos el SQL para crear la tabla
        statement.executeUpdate(sql);

        // Cerramos el statement
        statement.close();

        System.out.println("Tabla VENTAS creada con éxito.");
    }
}