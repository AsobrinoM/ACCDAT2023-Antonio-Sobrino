package com.mycompany.basededatos;

import java.sql.*;
import java.util.Properties;

public class BASEDEDATOS {

    static String driver;
    static Connection conexion;
    static DatabaseMetaData dbmd;

    public static void main(String[] args) {
        try {
            establecerConexion();
             dbmd=conexion.getMetaData();
       
            
            //insertarExecuteUpdate();
            //insertarExecute();
            //insertarPrepared();
            //crearTabla();
            //obtenerEmpleadoMaxSalario();
            //FILE2SELECT(10);
            // añadirCampo();
           // functodEmple();
          // funcionIva();
          laNominaCarajo();
            // consultaExecute();
            // consultaPrepared();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
 private static void consultaExecute() throws SQLException {
    String sql = "Select * from departamentos";
    Statement sentencia=conexion.createStatement();
    Boolean valor = sentencia.execute(sql);
    if (valor){
        ResultSet resul = sentencia.getResultSet();
        while(resul.next()){
            System.out.println("Número de departamento:" + resul.getInt(1) + " "+
                                "Nombre de departamento:" + resul.getString(2)+ " "+ 
                                "Localidad:"+ resul.getDate(3)+ " ");
        }       
        
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
    }  private static void consultaExecute()  {
    try {
        String sql = "Select * from departamentos";
        Statement sentencia=conexion.createStatement();
        Boolean valor = sentencia.execute(sql);
        if (valor){
            ResultSet resul = sentencia.getResultSet();
            while(resul.next()){
                System.out.println("Número de departamento:" + resul.getInt(1) + " "+ 
                        "Nombre de departamento:" + resul.getString(2)+ " "+
                                "Localidad:"+ resul.getString(3)+ " ");
            }
            
        }
    } catch (SQLException ex) {
        System.out.println ("Ha ocurrido una excepcion:");
        System.out.println ("Mensaje: " +ex.getMessage());  
        System.out.println ("SQL Estado: " +ex.getSQLState());
        System.out.println ("Código de error: " +ex.getErrorCode());
    }
    }
          private static void consultaEmpleados() throws SQLException {
    String sql = "Select * from EMPLEADOS";
    Statement sentencia=conexion.createStatement();
    Boolean valor = sentencia.execute(sql);
    if (valor){
        ResultSet resul = sentencia.getResultSet();
        while(resul.next()){
            System.out.println("Número de Empleado:" + resul.getInt(1) + " "+
                                "Apellido :" + resul.getString(2)+ " "+ 
                                "Oficio:"+ resul.getString(3)+ " "+
                                  "Fecha Alt:"+ resul.getDate(5)+ " "+
                                    "SALARIO:"+ resul.getInt(6)+ " "+
                                       "Comision:"+ resul.getInt(7)+ " "+
                                        "Departamento:"+resul.getInt(8));
        }       
        
    }
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
         public static void laNominaCarajo() throws SQLException{
       String sql="{?=call CALCULAR_NOMINA(? , ? , ?) }";
       CallableStatement llamada=conexion.prepareCall(sql);
       llamada.registerOutParameter(1,Types.VARCHAR);
       llamada.setInt(2, 1000);
       llamada.setInt(3, 500);
       llamada.setInt(4, 20);
       llamada.executeUpdate();
       String salida=llamada.getString(1);
       System.out.println(salida);
       /**
        * 
        * CREATE OR REPLACE FUNCTION CALCULAR_NOMINA(salario number, comision number,irpf number)
RETURN NUMBER
AS
    sinIrpf NUMBER;
BEGIN
    sinIrpf:=salario + comision-((salario+comision)*(irpf/100));
    RETURN sinIrpf;
END CALCULAR_NOMINA;
        */
   }
      public static void incrementoSal() throws SQLException {
    String sql = "{?=call incrementoSalarial(?, ?)}";
    CallableStatement llamada = conexion.prepareCall(sql);
    llamada.registerOutParameter(1, Types.INTEGER);
    llamada.setInt(2, 10);  // Departamento 10
    llamada.setInt(3, 100); // Incremento de salario
    llamada.executeUpdate();
    int salida = llamada.getInt(1);

    if (salida > 0) {
        System.out.println("Incremento salarial realizado con éxito en el departamento 10.");
    } else {
        System.out.println("No se realizaron incrementos de salario en el departamento 10.");
    }
}
      public static void GetNombreDep() throws SQLException {
    String sql = "{?= call GETNOMBREDEP(?)}";
    CallableStatement llamada = conexion.prepareCall(sql);
    llamada.registerOutParameter(1, Types.VARCHAR);
    llamada.setInt(2, 10);
    llamada.executeUpdate();
    String salida = llamada.getString(1);
    System.out.println(salida);
}
   private static void ejecturarFuncion(int dept) throws SQLException{
        String sql="{?=call nombre_depart_f(?)}";
         CallableStatement llamada=conexion.prepareCall(sql);
         
         llamada.registerOutParameter(1, Types.VARCHAR);
         
         llamada.setInt(2, dept);
         
         llamada.executeUpdate();
         
         String salida_return=llamada.getString(1);
         
         System.out.println("El nombre del departamento es: "+salida_return);
    }
      public static void FILE1INSERTMYSQL() throws SQLException{
         int dep=15;
         String dnombre="INFORMÁTICA";
         String loc="MADRID";
         String sql="insert into departamentos values("+dep+",'"+dnombre+"','"+loc+"')";
        // String sql="delete from departamentos where DNOMBRE='"+dnombre+"'";
         System.out.println("Se ha insertado correctamente");
       //  System.out.println("Se ha eliminado correctamente");
    try (Statement sentencia = conexion.createStatement()) {
        sentencia.executeUpdate(sql);
    }
     }

    public  static void consultaPrepared() throws SQLException{
         String sql = "Select * from departamentos";
    try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
        int filas = sentencia.executeUpdate();
        if (filas<0){
            try (ResultSet resul = sentencia.getResultSet()) {
                while(resul.next()){
                    System.out.println("Número de departamento:" + resul.getInt(1) + " "+
                            "Nombre de departamento:" + resul.getString(2)+ " "+
                                    "Localidad:"+ resul.getString(3)+ " ");
                }
            }
        }
}
    }
    public static void File3Vista() throws SQLException{
      String sql = "CREATE VIEW vista_emdep AS " +
              "SELECT DEPT_NO, " +
              "COUNT(*) AS NUM_EMPLE, " +
              "AVG(SALARIO) AS MEDIA_DE_SALARIO " +
              "FROM EMPLEADOS GROUP BY DEPT_NO";
        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){
            int filas =  sentencia.executeUpdate();
        }
    }
     private  static void insertarExecuteUpdate() throws SQLException{
     int dep=4;
     String dnombre="Suministros";
     String loc="Sevilla";
      String sql="insert into departamentos values ("+ dep +",'" +dnombre+ "','"+ loc+ "')";
   Statement sentencia = conexion.createStatement();
   int filas = sentencia.executeUpdate(sql);
   sentencia.close();
    
     }
      private static void insertarExecute()throws SQLException  {
     int dep=5;
     String dnombre="Exportacion";
     String loc="Bilbao";
      String sql="insert into departamentos values ("+ dep +",'" +dnombre+ "','"+ loc+ "')";
   Statement sentencia = conexion.createStatement();
   Boolean valor=sentencia.execute(sql);
   if(!valor){
       int filas=sentencia.getUpdateCount();
       System.out.println("Se han insertado:"+filas+"filas");
   }
   
   int filas = sentencia.executeUpdate(sql);
   sentencia.close();
    
    }
           private static void añadirCampo()throws SQLException {
               String sql= "alter table departamentos add (tlfn number:(9))";
               Statement sentencia = conexion.createStatement();
               int filas= sentencia.executeUpdate(sql);
               
               
               
    }
           private static void ejecutarProcedimiento() throws SQLException{
               String sql="{call nombre_depart_p(?,?)}";
               
               CallableStatement llamada = conexion.prepareCall(sql);
               int dep=1;
               llamada.setInt(1,dep);
               llamada.registerOutParameter(2, Types.VARCHAR);
               llamada.executeUpdate();
               String salida_return =  llamada.getString(2);
               System.out.println("El nombre del departamento es: "+salida_return);
               
           }
          private static void obtenerInformacionDeConexion() throws SQLException{
              String nombre=dbmd.getDatabaseProductName();
              
              //Driver utilizado
              String driver=dbmd.getDriverName();
              //Direccion para acceder a la BBDD
              String url=dbmd.getURL();
              //Nombre de usuario
              String usuario=dbmd.getUserName();
              System.out.println("Nombre del SGBD:"+nombre);
              System.out.println("Nombre del usuario:"+usuario);
              System.out.println("driver:"+driver);
              System.out.println("url:"+url);
             
          }

    private static void obtenerInformacionDeLasTablas() throws SQLException {
        ResultSet resul=null;
        String nombre_usuario;
        String nombre_tabla;
        String[] tipos={"TABLE"};
        resul = dbmd.getTables("ORCL18","C##DAM2",null,tipos);
        while (resul.next()){
            nombre_usuario = resul.getString("TABLE_SCHEM");
            nombre_tabla=resul.getString("TABLE_NAME");
            System.out.println("USUARIO:"+nombre_usuario+"TABLA:"+nombre_tabla);
        }
    }

    private static void obtenerInformacionDeLasColumnas() throws SQLException{
     
        ResultSet resul=dbmd.getColumns("ORCL18", "C##DAM2", "DEPARTAMENTOS", null);
        String nombre_tabla;
        String nombre_columna;
        while (resul.next()){
            nombre_columna = resul.getString("COLUMN_NAME");
            nombre_tabla=resul.getString("TABLE_NAME");
            System.out.println(" TABLA: "+nombre_tabla+" COLUMNA: "+nombre_columna);
        }
    }
 private static void obtenerInformacionDeLasClaves(String NTABLA) throws SQLException {
    // Obtener información sobre las claves primarias
    ResultSet primaryKeysResult = dbmd.getPrimaryKeys("ORCL18", "C##DAM2", NTABLA);
    
    System.out.println("Claves Primarias:");
    while (primaryKeysResult.next()) {
        String nombre_tabla = primaryKeysResult.getString("TABLE_NAME");
        String nombre_columna = primaryKeysResult.getString("COLUMN_NAME");
        System.out.println(" TABLA: " + nombre_tabla + " COLUMNA: " + nombre_columna);
    }

    // Obtener información sobre las claves ajenas
    ResultSet foreignKeysResult = dbmd.getImportedKeys("ORCL18", "C##DAM2", NTABLA);

    System.out.println("\nClaves Ajenas:");
    while (foreignKeysResult.next()) {
        String nombre_tabla = foreignKeysResult.getString("FKTABLE_NAME");
        String nombre_columna = foreignKeysResult.getString("FKCOLUMN_NAME");
        System.out.println(" TABLA: " + nombre_tabla + " COLUMNA: " + nombre_columna);
    }
    ResultSet exportedKeysResult = dbmd.getExportedKeys("ORCL18", "C##DAM2", NTABLA);
            System.out.println("\nClaves Ajenas de Otras Tablas que Apuntan a Nuestras Tablas:");
    while (exportedKeysResult.next()) {
        String nombre_tabla = exportedKeysResult.getString("FKTABLE_NAME");
        String nombre_columna = exportedKeysResult.getString("FKCOLUMN_NAME");
        System.out.println(" TABLA: " + nombre_tabla + " COLUMNA: " + nombre_columna);
    }
}   
  private static void obtenerInformacionDelResultSet(String NTABLA) throws SQLException{
      Statement sentencia=conexion.createStatement();
      ResultSet rs=sentencia.executeQuery("Select * from "+NTABLA);
      ResultSetMetaData rsmd=rs.getMetaData();
      int numColumnas=rsmd.getColumnCount();
      //Devuelve el nombre de la columna de la posicion "i"
      String nombre_columna = rsmd.getColumnName(2);
      //Devuelve el tipo de la columna
      String tipo_columna = rsmd.getColumnTypeName(2);
      // Devuelve 1 si la columna puede contener nulos
      int valores_nulos=rsmd.isNullable(2);
      //Devuelve el maximo de caracteres de la columna de la posicion "i"
      int tamaño_columna = rsmd.getColumnDisplaySize(2);
      
      System.out.println("NUMERO DE COLUMNAS DEVUELTAS:" + numColumnas);
      System.out.println("NOMBRE DE LA CAMPO:" + nombre_columna);
      System.out.println("TIPO DEL CAMPO:" + tipo_columna);
      System.out.println("TAMAÑO DEL CAMPO" + tamaño_columna);
      System.out.println("ACEPTA NULOS:" + ((valores_nulos==1)?"Si":"No"));
          
  }
     private static void obtenerNumeroFilasDevueltas(String NTABLA) throws SQLException{
           Statement sentencia=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
           ResultSet rs=sentencia.executeQuery("Select * from "+NTABLA);
           int rows=0; 
           
           if(rs.last()){
               rows=rs.getRow();
               rs.beforeFirst();
           }
           rs.close();
           sentencia.close();
           System.out.println("El numero de filas devueltas es de "+rows);
         
     }
}