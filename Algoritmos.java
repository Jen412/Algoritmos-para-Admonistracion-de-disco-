import java.util.Scanner;

import java.util.Arrays;
import java.util.*;

public class Algoritmos{
    static Scanner read = new Scanner(System.in);
    public static void main(String[] args) {
        int num_peticiones=0, cilin_ini=0;
        int[] peticiones;
        boolean ban = false;


        do {
            System.out.print("Ingrese el numero de Cilindros: ");
            String s = read.nextLine();
            if (validNum(s)==true) {
                final int cilin = Integer.parseInt(s);
                if (validaVal(cilin)==true) {
                    ban=true;
                }
                else{
                    System.out.println("El valor debe de ser entre mayor a 0 y menor a 199");
                }
            }
            else{
                System.out.println("No son validas las letras");
            }
        } while (ban==false);
        ban=false;
        do {
            System.out.print("Ingrese el numero de Peticiones: ");
            String s = read.nextLine();
            if (validNum(s)==true) {
                num_peticiones= Integer.parseInt(s);
                ban=true;
            }
            else{
                System.out.println("No son validas las letras");
            }
        } while (ban==false);
        peticiones = new int[num_peticiones];
        ban=false;


        for (int i = 0; i < peticiones.length; i++) {
            do {
                System.out.print("Peticion "+(i+1) +": ");
                String s = read.nextLine();
                if (validNum(s)==true) {
                    peticiones[i] = Integer.parseInt(s);
                    if (validaVal(peticiones[i])==true) {
                        ban=true;
                    }
                    else{
                        System.out.println("El valor debe de ser entre mayor a 0 y menor a 199");
                    }
                }
                else{
                    System.out.println("No son validas las letras");
                }   
            } while (ban==false);
        }
        ban=false;

        do {
            System.out.print("Ingrese el cilindro cilin_ini: ");
            String s = read.nextLine();
            if (validNum(s)==true) {
                cilin_ini = Integer.parseInt(s);
                if (validaVal(cilin_ini)==true) {
                    ban=true;
                }
                else{
                    System.out.println("El valor debe de ser entre mayor a 0 y menor a 199");
                }
            }
            else{
                System.out.println("No son validas las letras");
            }
        } while (ban==false);
        ban=false;

        String s;
        char opc;
        do {
            menu();
            s=read.next();
            opc = s.charAt(0);
            switch (opc) {
                case '1':
                    FCFS(num_peticiones,cilin_ini, peticiones);
                    break;
                case '2':
                    SSTF(cilin_ini, peticiones, num_peticiones);
                    break;
                case '3':
                    SCAN(cilin_ini, peticiones, num_peticiones);
                    break;
                case '4':
                    CSCAN(num_peticiones, peticiones, cilin_ini);
                    break;
                case '5':
                    LOOK(peticiones, cilin_ini, num_peticiones);
                    break;  
                case '6':
                    CLOOK(cilin_ini, peticiones,  num_peticiones);
                    break;
                case '7':
                    ban=true;
                    System.out.println("Programa creado por: ");
                    System.out.println("\tJuan Fernando Brambila Rivera");    
                    System.out.println("\tLuis Angel Guzman Garcia");    
                    System.out.println("\tJose Eduardo Peña Jimenez");    
                    System.out.println("\tKarla Daniela Melchor Olguin");    
                    break;          
                default:
                    break;
            }
        } while (ban==false);
    }

    public static void menu() {
        System.out.println("Algoritmos de discos duros");
        System.out.println("FCFS   [1]");
        System.out.println("SSTF   [2]");
        System.out.println("SCAN   [3]");
        System.out.println("C-SCAN [4]");
        System.out.println("LOOK   [5]");
        System.out.println("C_LOOK [6]");
        System.out.println("Salir  [7]");
        System.out.print("Opcion a realizar [1-7]:");
    }

    public static boolean validNum(String s) {
        boolean b=false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4'
                    || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8'
                    || s.charAt(i) == '9') {
                b = true;
            }
        }
        return b;
    }

    public static boolean validaVal(int n) {
        boolean b = false;
        if(n>=0 && n<200){
            b=true;
        }
        return b;
    }
    public static void SCAN(int cilin_ini, int peticiones[], int num_peticiones) {
        int totalDespla = 0, totalTiempo = 0,contMayor=0, contMenor=0, cont=0, bControl=0;
        int datos[][];
        Integer mayor[];
        Integer menor[];
        int todos[];
        
        Vector left = new Vector();
        Vector right = new Vector();
        
        
        System.out.print("Ingrese el bit de Control [0 o 1]: ");     
        bControl = read.nextInt();
        
        for (int i = 0; i <num_peticiones; i++) {
            if (peticiones[i] < cilin_ini) {
                left.add(peticiones[i]);
                contMenor++;
            }
            if (peticiones[i] > cilin_ini) {
                right.add(peticiones[i]);
                contMayor++;
            }
        }
        left.add(0);
        contMenor++;
        
        Collections.sort(left);
        Collections.sort(right);
        
        menor = new Integer[contMenor];
        mayor = new Integer[contMayor];
        
        todos = new int[contMenor+contMayor];
        datos = new int [4][todos.length];

        int run = 2;
        while (run-- >0){
            if (bControl == 0) {
                for (int i = contMenor - 1; i >= 0; i--){
                    menor [i] = (Integer) left.elementAt(i);
                }
                Arrays.sort(menor, Collections.reverseOrder());
                bControl = 1;
            }
            else if (bControl == 1) 
            {
                for (int i = 0; i < contMayor; i++) 
                {
                    mayor[i] = (Integer) right.elementAt(i);
                }
                bControl = 0;
            }
        }

       for(int i=0; i<contMenor;i++){
            todos[i]=menor[i];
            cont++;
        }
        for(int i=0; i<contMayor;i++){
            todos[i+cont]=mayor[i];
        }
         //codigo pinky    
        if (bControl == 0) {
            for (int i = 0; i < todos.length; i++) {
                if (i == 0) {
                    datos[0][i] = cilin_ini;
                    datos[1][i] = todos[i];
                    datos[2][i] = 0;
                    int aux = cilin_ini - todos[i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }

                } else {
                    datos[0][i] = todos[i - 1];
                    datos[1][i] = todos[i];
                    datos[2][i] = datos[2][i - 1] + datos[3][i - 1];
                    int aux = datos[0][i] - datos[1][i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }
                }
            }
        }
        if (bControl == 1) {

            for (int i = 0; i < contMayor; i++) {
                todos[i] = mayor[i];
            }
            for (int i = 0; i < contMenor; i++) {
                todos[i+cont] = menor[i];
                cont++;
            }

            for (int i = 0; i < todos.length; i++) {
                if (i == 0) {
                    datos[0][i] = cilin_ini;
                    datos[1][i] = todos[i];
                    datos[2][i] = 0;
                    int aux = cilin_ini - todos[i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }

                } else {
                    datos[0][i] = todos[i - 1];
                    datos[1][i] = todos[i];
                    datos[2][i] = datos[2][i - 1] + datos[3][i - 1];
                    int aux = datos[0][i] - datos[1][i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }
                }
            }
        }
       System.out.printf("\n%-10s %-10s %-10s %-10s \n", "Cilindro Actual", "Cilindro solicitado", "Tiempo de espera", "Desplazamiento");
        for (int i = 0; i < todos.length; i++) {
            System.out.printf(" %-17d %-20d %-17d %-15d \n", datos[0][i], datos[1][i], datos[2][i], datos[3][i]);
        }
        for (int i = 0; i < todos.length; i++) {
            totalTiempo += datos[2][i];
            totalDespla += datos[3][i];
        }
        System.out.printf("\nDesplazamiento: %d\n", totalDespla);
        System.out.printf("Tiempo promedio de espera: %.3f\n", (double) totalTiempo /num_peticiones);
    }

    public static void FCFS(int num_peticiones, int cilin_ini, int[] peticiones) {
        int datos[][];
        int  totalDespla = 0, totalTiempo = 0;
        datos = new int[4][num_peticiones];
		for(int i=0; i<num_peticiones; i++) {
			if(i==0) {
				datos[0][i] = cilin_ini;
				datos[1][i] = peticiones[i];
				datos[2][i] = 0;
				int aux = cilin_ini-peticiones[i];
				if(aux < 0) datos[3][i] = aux*(-1); 
				else datos[3][i] = aux;
				 
			}
			else {
				datos[0][i] = peticiones[i-1];
				datos[1][i] = peticiones[i];
				datos[2][i] = datos[2][i-1]+datos[3][i-1];
				int aux = datos[0][i]-datos[1][i];
				if(aux < 0) datos[3][i] = aux*(-1); 
				else datos[3][i] = aux;
			}
		}
		System.out.printf("\n%-10s %-10s %-10s %-10s \n", "Cilindro Actual", "Cilindro solicitado", "Tiempo de espera", "Desplazamiento");
		for(int i=0; i<num_peticiones; i++) {
			System.out.printf(" %-17d %-20d %-17d %-15d \n", datos[0][i], datos[1][i], datos[2][i], datos[3][i]);
		}
		for(int i=0; i<num_peticiones; i++) {
			totalTiempo += datos[2][i]; 
			totalDespla += datos[3][i];		
		}
		System.out.printf("\nDesplazamiento: %d\n", totalDespla);
		System.out.printf("Tiempo promedio de espera: %.3f\n", (double)totalTiempo/num_peticiones);
        System.out.println();
    }

    public static void SSTF(int cilin_ini, int[] peticiones, int num_peticiones) {
        int  totalDespla = 0, totalTiempo = 0, desplazamiento1 = 0, desplazamiento2 = 0, indice = 0; 
		int datos[][];
		ArrayList<Integer> procesos = new ArrayList<Integer>();
		
		
		for(int i=0; i<num_peticiones; i++) {
			procesos.add(peticiones[i]); 
		}
		
		
		Collections.sort(procesos);		// odenamos de menor a mayor
		datos = new int[4][num_peticiones];
		
		for(int c=procesos.size()-1; c>=0; c--) {
			if(procesos.get(c)>cilin_ini) {
				indice = c;
			}
		}
      
        if(indice>0){
            desplazamiento1 = Math.abs(cilin_ini-procesos.get(indice));
            desplazamiento2 = Math.abs(cilin_ini-procesos.get(indice-1));                        
        }
        else{
            desplazamiento1 = Math.abs(cilin_ini-procesos.get(indice));
            desplazamiento2 = desplazamiento1+5;            
        }

        if(desplazamiento1 < desplazamiento2) {
            datos[3][0] = desplazamiento1;
            datos[1][0] = procesos.get(indice);
            procesos.remove(indice);
        }
        else if(desplazamiento2 < desplazamiento1){
            datos[3][0] = desplazamiento2;
            datos[1][0] = procesos.get(indice-1);
            procesos.remove(indice-1);
        }  		
		
		for(int i=0; i<num_peticiones; i++) {
			if(i==0) {
				datos[0][i] = cilin_ini;
				datos[2][i] = 0; 
			}
			else {
				for(int c=procesos.size()-1; c>=0; c--) {
					if(procesos.get(c)>=datos[1][i-1]) {
					   indice = c;   		
					}
				}
            if(indice>0){
               desplazamiento1 = Math.abs(procesos.get(indice)-datos[1][i-1]);
               desplazamiento2 = Math.abs(procesos.get(indice-1)-datos[1][i-1]);                        
            }
            else{
               desplazamiento1 = Math.abs(procesos.get(indice)-datos[1][i-1]);
               desplazamiento2 = desplazamiento1+5;            
            }
            
            if(desplazamiento1 < desplazamiento2) {
               datos[3][i] = desplazamiento1;
               datos[1][i] = procesos.get(indice);
               procesos.remove(indice);
            }
            else if(desplazamiento2 < desplazamiento1){
               datos[3][i] = desplazamiento2;
               datos[1][i] = procesos.get(indice-1);
               procesos.remove(indice-1);
            }  		
				datos[0][i] = datos[1][i-1];
				datos[2][i] = datos[2][i-1]+datos[3][i-1];
			}        
		}
			
      System.out.printf("\n%-10s %-10s %-10s %-10s \n", "Cilindro Actual", "Cilindro solicitado", "Tiempo de espera", "Desplazamiento");
		for(int i=0; i<num_peticiones; i++) {
			System.out.printf(" %-17d %-20d %-17d %-15d \n", datos[0][i], datos[1][i], datos[2][i], datos[3][i]);
		}
      
      for(int i=0; i<num_peticiones; i++) {
			totalTiempo += datos[2][i]; 
			totalDespla += datos[3][i];		
		}
		System.out.printf("\nDesplazamiento: %d\n", totalDespla);
		System.out.printf("Tiempo promedio de espera: %.3f\n", (double)totalTiempo/num_peticiones);
	}

    public static void CSCAN(int num_peticiones, int[] peticiones, int cilin_ini) {
        int  totalDespla = 0, totalTiempo = 0,contMayor=0, contMenor=0, cont=0,bControl=0;
        int datos[][];
        Integer mayor[];
        Integer menor[];
        int todos[];
        
        Vector left = new Vector();
        Vector right = new Vector();
           
        System.out.print("Ingresa el bite ya sea 1 o 0: ");
        bControl=read.nextInt();
        
        for (int i = 0; i < num_peticiones; i++) {
            if (peticiones[i] < cilin_ini) {
                left.add(peticiones[i]);
                contMenor++;
            }
            if (peticiones[i] > cilin_ini) {
                right.add(peticiones[i]);
                contMayor++;
            }
        }
        right.add(199);
        contMayor++;
        left.add(0);
        contMenor++;
        
        Collections.sort(left);
        Collections.sort(right);
        
        menor = new Integer[contMenor];
        mayor = new Integer[contMayor];
        
       
        todos = new int[contMenor+contMayor];
        datos = new int [4][todos.length];
        
        for (int i =0; i < contMenor; i++) {
            menor[i] = (Integer) left.elementAt(i);
        }
              
        Arrays.sort(menor, Collections.reverseOrder());
        
        
        for(int i=0; i<contMayor; i++){
            mayor[i] = (Integer) right.elementAt(i);
        }
        Arrays.sort(mayor, Collections.reverseOrder());
        if (bControl == 1) {
            for (int i = 0; i < contMenor; i++) {
                todos[i] = menor[i];
                cont++;
            }
            for (int i = 0; i < contMayor; i++) {
                todos[i + cont] = mayor[i];
            }

            for (int i = 0; i < todos.length; i++) {
                if (i == 0) {
                    datos[0][i] = cilin_ini;
                    datos[1][i] = todos[i];
                    datos[2][i] = 0;
                    int aux = cilin_ini - todos[i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }

                } else {
                    datos[0][i] = todos[i - 1];
                    datos[1][i] = todos[i];
                    datos[2][i] = datos[2][i - 1] + datos[3][i - 1];
                    int aux = datos[0][i] - datos[1][i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }
                }
            }
        }
        if (bControl == 0) {

            for (int i = 0; i < contMayor; i++) {
                todos[i ] = mayor[i];
            }
            for (int i = 0; i < contMenor; i++) {
                todos[i+ cont] = menor[i];
                cont++;
            }

            for (int i = 0; i < todos.length; i++) {
                if (i == 0) {
                    datos[0][i] = cilin_ini;
                    datos[1][i] = todos[i];
                    datos[2][i] = 0;
                    int aux = cilin_ini - todos[i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }

                } else {
                    datos[0][i] = todos[i - 1];
                    datos[1][i] = todos[i];
                    datos[2][i] = datos[2][i - 1] + datos[3][i - 1];
                    int aux = datos[0][i] - datos[1][i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }
                }
            }
        }
        
       
       
       System.out.printf("\n%-10s %-10s %-10s %-10s \n", "Cilindro Actual", "Cilindro solicitado", "Tiempo de espera", "Desplazamiento");
        for (int i = 0; i < todos.length; i++) {
            System.out.printf(" %-17d %-20d %-17d %-15d \n", datos[0][i], datos[1][i], datos[2][i], datos[3][i]);
        }
        for (int i = 0; i < todos.length; i++) {
            totalTiempo += datos[2][i];
            totalDespla += datos[3][i];
        }
        System.out.printf("\nDesplazamiento: %d\n", totalDespla);
        System.out.printf("Tiempo promedio de espera: %.3f\n", (double) totalTiempo / num_peticiones);
    }

    public static void LOOK(int peticiones[], int cilin_ini, int num_peticiones){ 
        int totalDespla = 0, totalTiempo = 0,contMayor=0, contMenor=0, cont=0, bControl=0;
        int datos[][];
        Integer mayor[];
        Integer menor[];
        int todos[];
        
        Vector left = new Vector();
        Vector right = new Vector();
        
        
        System.out.print("Ingrese el bit de Control [0 o 1]: ");     
        bControl = read.nextInt();
        
        for (int i = 0; i <num_peticiones; i++) {
            if (peticiones[i] < cilin_ini) {
                left.add(peticiones[i]);
                contMenor++;
            }
            if (peticiones[i] > cilin_ini) {
                right.add(peticiones[i]);
                contMayor++;
            }
        }
        
        Collections.sort(left, Collections.reverseOrder());
        Collections.sort(right);
        
        menor = new Integer[contMenor];
        mayor = new Integer[contMayor];
        
        todos = new int[contMenor+contMayor];
        datos = new int [4][todos.length];

        int run = 2;
        while (run-- >0){
            if (bControl == 0) {
                for (int i=0; i<contMenor; i++){
                    menor [i] = (Integer) left.elementAt(i);
                }
                Arrays.sort(menor, Collections.reverseOrder());
                bControl = 1;
            }
            else if (bControl == 1) 
            {
                for (int i = 0; i < contMayor; i++) 
                {
                    mayor[i] = (Integer) right.elementAt(i);
                }
                bControl = 0;
            }
        }

       for(int i=0; i<contMenor;i++){
            todos[i]=menor[i];
            cont++;
        }
        for(int i=0; i<contMayor;i++){
            todos[i+cont]=mayor[i];
        }
         //codigo pinky    
         if (bControl == 0) {
            for (int i = 0; i < todos.length; i++) {
                if (i == 0) {
                    datos[0][i] = cilin_ini;
                    datos[1][i] = todos[i];
                    datos[2][i] = 0;
                    int aux = cilin_ini - todos[i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }

                } else {
                    datos[0][i] = todos[i - 1];
                    datos[1][i] = todos[i];
                    datos[2][i] = datos[2][i - 1] + datos[3][i - 1];
                    int aux = datos[0][i] - datos[1][i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }
                }
            }
        }
        if (bControl == 1) {

            for (int i = 0; i < contMayor; i++) {
                todos[i] = mayor[i];
            }
            for (int i = 0; i < contMenor; i++) {
                todos[i+cont] = menor[i];
                cont++;
            }

            for (int i = 0; i < todos.length; i++) {
                if (i == 0) {
                    datos[0][i] = cilin_ini;
                    datos[1][i] = todos[i];
                    datos[2][i] = 0;
                    int aux = cilin_ini - todos[i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }

                } else {
                    datos[0][i] = todos[i - 1];
                    datos[1][i] = todos[i];
                    datos[2][i] = datos[2][i - 1] + datos[3][i - 1];
                    int aux = datos[0][i] - datos[1][i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }
                }
            }
        }
       System.out.printf("\n%-10s %-10s %-10s %-10s \n", "Cilindro Actual", "Cilindro solicitado", "Tiempo de espera", "Desplazamiento");
        for (int i = 0; i < todos.length; i++) {
            System.out.printf(" %-17d %-20d %-17d %-15d \n", datos[0][i], datos[1][i], datos[2][i], datos[3][i]);
        }
        for (int i = 0; i < todos.length; i++) {
            totalTiempo += datos[2][i];
            totalDespla += datos[3][i];
        }
        System.out.printf("\nDesplazamiento: %d\n", totalDespla);
        System.out.printf("Tiempo promedio de espera: %.3f\n", (double) totalTiempo /num_peticiones);
    } 

    public static void CLOOK(int cilin_ini, int peticiones[], int num_peticiones) {
        int totalDespla = 0, totalTiempo = 0,contMayor=0, contMenor=0, cont=0, bControl=0;
        int datos[][];
        Integer mayor[];
        Integer menor[];
        int todos[];
        
        Vector left = new Vector();
        Vector right = new Vector();
        
        
        System.out.print("Ingrese el bit de Control [0 o 1]: ");     
        bControl = read.nextInt();
        
        for (int i = 0; i <num_peticiones; i++) {
            if (peticiones[i] < cilin_ini) {
                left.add(peticiones[i]);
                contMenor++;
            }
            if (peticiones[i] > cilin_ini) {
                right.add(peticiones[i]);
                contMayor++;
            }
        }
        
        Collections.sort(left);
        Collections.sort(right, Collections.reverseOrder());
        
        menor = new Integer[contMenor];
        mayor = new Integer[contMayor];

        todos = new int[contMenor+contMayor];
        datos = new int [4][todos.length];

        int run = 2;
        while (run-- >0){
            if (bControl == 0) {
                for (int i = contMenor - 1; i >= 0; i--){
                    menor [i] = (Integer) left.elementAt(i);
                }
                Arrays.sort(menor, Collections.reverseOrder());
                bControl = 1;
            }
            else if (bControl == 1) 
            {
                for (int i = 0; i < contMayor; i++) 
                {
                    mayor[i] = (Integer) right.elementAt(i);
                }
                bControl = 0;
            }
        }

       for(int i=0; i<contMenor;i++){
            todos[i]=menor[i];
            cont++;
        }
        for(int i=0; i<contMayor;i++){
            todos[i+cont]=mayor[i];
        }
         //codigo pinky    
        if (bControl == 0) {
            for (int i = 0; i < todos.length; i++) {
                if (i == 0) {
                    datos[0][i] = cilin_ini;
                    datos[1][i] = todos[i];
                    datos[2][i] = 0;
                    int aux = cilin_ini - todos[i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }

                } else {
                    datos[0][i] = todos[i - 1];
                    datos[1][i] = todos[i];
                    datos[2][i] = datos[2][i - 1] + datos[3][i - 1];
                    int aux = datos[0][i] - datos[1][i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }
                }
            }
        }
        if (bControl == 1) {

            for (int i = 0; i < contMayor; i++) {
                todos[i] = mayor[i];
            }
            for (int i = 0; i < contMenor; i++) {
                todos[i+cont] = menor[i];
                cont++;
            }

            for (int i = 0; i < todos.length; i++) {
                if (i == 0) {
                    datos[0][i] = cilin_ini;
                    datos[1][i] = todos[i];
                    datos[2][i] = 0;
                    int aux = cilin_ini - todos[i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }

                } else {
                    datos[0][i] = todos[i - 1];
                    datos[1][i] = todos[i];
                    datos[2][i] = datos[2][i - 1] + datos[3][i - 1];
                    int aux = datos[0][i] - datos[1][i];
                    if (aux < 0) {
                        datos[3][i] = aux * (-1);
                    } else {
                        datos[3][i] = aux;
                    }
                }
            }
        }
       System.out.printf("\n%-10s %-10s %-10s %-10s \n", "Cilindro Actual", "Cilindro solicitado", "Tiempo de espera", "Desplazamiento");
        for (int i = 0; i < todos.length; i++) {
            System.out.printf(" %-17d %-20d %-17d %-15d \n", datos[0][i], datos[1][i], datos[2][i], datos[3][i]);
        }
        for (int i = 0; i < todos.length; i++) {
            totalTiempo += datos[2][i];
            totalDespla += datos[3][i];
        }
        System.out.printf("\nDesplazamiento: %d\n", totalDespla);
        System.out.printf("Tiempo promedio de espera: %.3f\n", (double) totalTiempo /num_peticiones);
    }
}