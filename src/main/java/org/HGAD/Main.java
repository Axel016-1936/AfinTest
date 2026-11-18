package org.HGAD;
import java.util.Scanner;
public class Main 
{
  // Declaración de Arreglos, variables y "Objetos" globales
    // Columnas:
    // 0 = Nombre
    // 1 = Correo
    // 2 = Rango
    // 3 = Contraseña
    static String[][] ListaUsuarios = new String[1000][4];
    static int contadorUsuarios = 0; //
    // Lleva el control de cuántos usuarios se han registrado
    static Scanner scanner = new Scanner(System.in);
    // Escaner global
    static boolean EsAdmin = false;
    // Variable a usar en el futuro
    static String[][] datosdisco = new String[1000][9];
    static int contadorDiscos = 0;

    static boolean InicioSesion = false;

    // INICIO DEL CODIGO DEL SISTEMA
    // MENU PRINCIPAL
    public static void main(String[] args) {
        int opcion = 0;

        do {

            System.out.println("===========================");
            System.out.println("      DISCOS SALVAJES");
            System.out.println("===========================");
            System.out.println("0. Salir");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("//Para ingresar al modulo de registro de datos del disco, registrese primero como ");
            System.out.println("administrador, inicie sesión y posteriormente ingrese el número 3 ");
            System.out.println("4.-Consulta de discos (Opción temporal)");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.next();
                System.out.println("Opción inválida. Ingrese un número.");
                continue;
            }


            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    break;
                case 3:
                    System.out.println("no se");
                    registrodiscos();
                    break;
                case 4:
                    ordenamiento();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    // MODULO DE REGISTRO DE USUARIOS
    public static void registrarUsuario() {
        System.out.println("--- MÓDULO DE REGISTRO ---");
        System.out.println("Ingrese su nombre");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su correo");
        String correo = scanner.nextLine();

        // Valida si el correo ya está registrado previamente
        for (int i = 0; i < contadorUsuarios; i++) {
            if (ListaUsuarios[i][1].equals(correo)) {
                System.out.println("Este correo ya se encuentra registrado.");
                return;
            }
        }

        System.out.println("Ingrese su rango");
        System.out.println("1. Usuario");
        System.out.println("2. Administrador");
        String rango = scanner.nextLine();
        while (!rango.equals("1") && !rango.equals("2")) {
            System.out.println("Rango incorrecto");
            System.out.println("1. Usuario");
            System.out.println("2. Administrador");
            rango = scanner.nextLine();
        }
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        // Almacena datos en la matriz global
        ListaUsuarios[contadorUsuarios][0] = nombre;
        ListaUsuarios[contadorUsuarios][1] = correo;
        ListaUsuarios[contadorUsuarios][2] = rango;
        ListaUsuarios[contadorUsuarios][3] = contrasena;

        contadorUsuarios++;

        System.out.println("¡Usuario registrado con éxito!");
    }

    // MODULO DE INICIO DE SESION
    public static void iniciarSesion() {
        System.out.println("--- MÓDULO DE INICIO DE SESIÓN ---");
        System.out.print("Ingrese su correo: ");
        String correoIngresado = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String contrasenaIngresada = scanner.nextLine();

        boolean usuarioEncontrado = false;
        for (int i = 0; i < contadorUsuarios; i++) {
            if (ListaUsuarios[i][1].equals(correoIngresado)) {
                usuarioEncontrado = true;

                // Verifica si la contraseña coincide
                if (ListaUsuarios[i][3].equals(contrasenaIngresada)) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    if (ListaUsuarios[i][2].equals("1")) {
                        EsAdmin = false;
                    } else if (ListaUsuarios[i][2].equals("2")) {
                        EsAdmin = true;
                    }
                    System.out.println("Bienvenido, " + ListaUsuarios[i][0] + " Admin: " + EsAdmin);
                    InicioSesion = true;
                } else {
                    System.out.println("Contraseña incorrecta.");
                }
                break;
            }
        }

        if (!usuarioEncontrado) {
            System.out.println("El correo ingresado no existe en el sistema.");
        }
    }

    //MODULO DE ORDENAMIENTO
    public static void ordenamiento() {
        System.out.println("========Modulo de consulta=========");
        if (contadorDiscos == 0) {
            System.out.println("Lo sentimos, el sistema no posee registro de algún disco");
        } else
        {
            boolean Condicion = false;
            int numero = 0;
            int opcion;
            do
            {
                System.out.println("Ingrese el Título del Disco:");
                String buscatitle=scanner.nextLine();
                System.out.println("Ingrese el artista:");
                String buscaartist = scanner.nextLine();
                System.out.println("Ingrese el género:");
                String buscagenero = scanner.nextLine();
                System.out.println("Ingrese Subgenero:");
                String buscarsubg = scanner.nextLine();
                System.out.println("Ingrese año de publicación:");
                String buscaraniop = scanner.nextLine();
                while (!Condicion) {
                    try {
                        numero = Integer.parseInt(buscaraniop);
                        if (numero > 0)
                        {
                            Condicion = true;
                        } else {
                            System.out.println("Error. el año de publicación debe ser mayor a 0");
                            buscaraniop = scanner.nextLine();
                        }

                    } catch (NumberFormatException e)
                    {
                        System.out.println("Error, inserta un número valido");
                        buscaraniop = scanner.nextLine();
                    }
                }
                Condicion = false;
                boolean encontrado = false;
                for (int i = 0; i < contadorDiscos; i++) {
                /*
                    usamos un boolea para encontrar las coincidencias de los discos que busca el usuario,
                    donde usamos un iqualsIgnoreCase para que sin importar que increse con mayusculas o minúsculas
                    los datos, se pueda ingresar los datos correctamente, y .trim para que si se ingres un espacio adicional
                    no haya algún error o inconcistencia, es decir, lo convierte tal cual esta registrado
                 */
                    boolean coincideTitulo = datosdisco[i][0].equalsIgnoreCase(buscatitle.trim());
                    boolean coincideArtista = datosdisco[i][1].equalsIgnoreCase(buscaartist.trim());
                    boolean coincideGenero = datosdisco[i][2].equalsIgnoreCase(buscagenero.trim());
                    boolean coincideSubg = datosdisco[i][3].equalsIgnoreCase(buscarsubg.trim());
                /*
                .equals se usa unicamente para evaluar la condición a cumplir, String.valueOf,
                vuelve a transformar la variable de tipo int (entero) a String (texto).
                 */
                    boolean coincideAnio = datosdisco[i][4].equals(String.valueOf(numero));

                    if (coincideTitulo && coincideArtista && coincideGenero && coincideSubg && coincideAnio) {
                        System.out.println("===========Datos del Disco===========");
                        System.out.println("Título: " + datosdisco[i][0]);
                        System.out.println("Artista: " + datosdisco[i][1]);
                        System.out.println("Genero: " + datosdisco[i][2]);
                        System.out.println("Subgenero: " + datosdisco[i][3]);
                        System.out.println("Año: " + datosdisco[i][4]);
                        System.out.println("Estado del Disco: " + datosdisco[i][5]);
                        System.out.println("Precio: $" + datosdisco[i][6]);
                        System.out.println("Formato Físico: " + datosdisco[i][7]);
                        System.out.println("Cantidad en Stock: " + datosdisco[i][8] + " unidades");
                        encontrado = true;
                    }
                }
                if (!encontrado)
                {
                    System.out.println("No se encuentra en existencia el disco solicitado");
                }
                System.out.println("¿Desea continuar?");
                System.out.println("1.-Si");
                System.out.println("2.-No");
                opcion=0;
                while (opcion!=1 && opcion!=2)
                {
                    if (scanner.hasNextInt())
                    {
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        if (opcion != 1 && opcion != 2) {
                            System.out.println("Error. Ingrese un número entre el 1 y el 2:");
                        }
                    }
                    else
                    {
                        scanner.nextLine();
                        System.out.println("Error. Ingrese un número válido (1 o 2):");
                    }
                }
            }while(opcion!=2);
        }

    }

    //MODULO DE REGISTRO DE DISCOS
    public static void registrodiscos() {
        int opcion;
        int numero;
        boolean Condicion = false;
        if (EsAdmin) {
            if (contadorDiscos >= datosdisco.length) {
                System.out.println("Error: La base de datos de discos está llena. No se pueden registrar más álbumes.");

            } else
            {
                System.out.println("======Modulo de registro de discos======");
                System.out.println("Bienvenido Administrador, ingrese los datos de los dicos a registrar");
                do
                {
                    System.out.println("1.-Título del disco:");
                    datosdisco[contadorDiscos][0] = scanner.nextLine();
                    for (int i = 0; i < contadorDiscos; i++) {
                        if (datosdisco[i][0].equalsIgnoreCase(datosdisco[contadorDiscos][0])) {
                            System.out.println("Disco con título en existencia");
                            return;
                        }
                    }
                    System.out.println("2.-Artista:");
                    datosdisco[contadorDiscos][1] = scanner.nextLine();
                    System.out.println("3.-Género:");
                    datosdisco[contadorDiscos][2] = scanner.nextLine();
                    System.out.println("4.-Subgénero:");
                    datosdisco[contadorDiscos][3] = scanner.nextLine();
                    System.out.println("5.-Año de publicación:");
                    datosdisco[contadorDiscos][4] = scanner.nextLine();
                    while (!Condicion) {
                        try {
                            numero = Integer.parseInt(datosdisco[contadorDiscos][4]);
                            if (numero > 0) {
                                Condicion = true;
                            } else {
                                System.out.println("Error, el año debe ser mayor a 0. Intente de nuevo:");
                                datosdisco[contadorDiscos][4] = scanner.nextLine().trim();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error, inserta un numero valido");
                            datosdisco[contadorDiscos][4] = scanner.nextLine();
                        }
                    }
                    Condicion = false;
                    System.out.println("6.-Estado del Disco (Mint, Near Mint, VG+, VG, G):");
                    datosdisco[contadorDiscos][5] = scanner.nextLine();
                    while (!datosdisco[contadorDiscos][5].equalsIgnoreCase("Mint") &&
                            !datosdisco[contadorDiscos][5].equalsIgnoreCase("Near Mint") &&
                            !datosdisco[contadorDiscos][5].equalsIgnoreCase("VG+") &&
                            !datosdisco[contadorDiscos][5].equalsIgnoreCase("VG") &&
                            !datosdisco[contadorDiscos][5].equalsIgnoreCase("G")) {
                        System.out.println("Error, ingrese una opción valida");
                        datosdisco[contadorDiscos][5] = scanner.nextLine();
                    }
                    System.out.println("7.-Precio ($):");
                    datosdisco[contadorDiscos][6] = scanner.nextLine();
                    while (!Condicion ) {
                        try {
                            numero = Integer.parseInt(datosdisco[contadorDiscos][6]);
                            if (numero > 0) {
                                Condicion = true;
                            } else {
                                System.out.println("Error, el precio debe ser mayor a 0. Intente de nuevo");
                                datosdisco[contadorDiscos][6] = scanner.nextLine();

                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error, inserta un numero valido");
                            datosdisco[contadorDiscos][6] = scanner.nextLine();
                        }
                    }
                    Condicion = false;
                    System.out.println("8.-Formato Físico");
                    datosdisco[contadorDiscos][7] = scanner.nextLine();
                    System.out.println("9.-Cantidad en Stock");
                    datosdisco[contadorDiscos][8] = scanner.nextLine();
                    while (!Condicion) {
                        try {
                            numero = Integer.parseInt(datosdisco[contadorDiscos][8]);
                            if (numero > 0) {
                                Condicion = true;
                            } else {
                                System.out.println("Error, la cantidad en stock debe ser mayor a 0. Intente de nuevo");
                                Integer.parseInt(datosdisco[contadorDiscos][8]);

                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error, inserta un numero valido");
                            datosdisco[contadorDiscos][8] = scanner.nextLine();
                        }
                    }
                    contadorDiscos++;
                    System.out.println("¡Disco registrado con éxito!");
                    System.out.println("¿Desea registrar otro disco?");
                    System.out.println("1.-Si");
                    System.out.println("2.-No");
                    opcion=0;
                    while (opcion!=1 && opcion!=2)
                        {
                        if (scanner.hasNextInt())
                        {
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                            if (opcion != 1 && opcion != 2)
                            {
                                System.out.println("Error. Ingrese un número entre el 1 y el 2:");
                            }
                        }
                        else
                        {
                            scanner.nextLine();
                            System.out.println("Error. Ingrese un número válido (1 o 2):");
                        }
                    }
                    /*scanner.hasNextInt(), revisa lo que escribió el usuario en el teclado antes de intentar guardarlo en una variable int.
                    Devuelve true si el dato es un número entero válido y false si el usuario escribió letras o símbolo.
                     */
                }
                while (opcion!=2);
            }
        }
    }
}


// Tratar de hacer los siguientes modulos en este mismo archivo
// para asi verificar la compatibilidad entre todos los modulos
