/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import ObjetosBase.*;
import Interface.*;
import java.io.*;

/**
 *
 * @author roso
 */
public class ControlarorServiciosContratables {
    
    public void VerPlanes(){
        
       
                MOCEL2000 p1 = new MOCEL2000();
                System.out.println(
                    "Lista de planes que ofrecemos para usted \n op   Descripcion \n"
                   +" 1  "+ p1.getNombre()+ " un plan " +p1.getTipo()+
                        "  por un costo de: Bs "+p1.getTarifa());

                MIXTOPLUS p2 = new MIXTOPLUS();
                System.out.println(
                   " 2  "+ p2.getNombre()+ " un plan " +p2.getTipo()+
                        "  por un costo de: Bs "+p2.getTarifa());
                
                System.out.println("\n Elija la opcion del plan que desea activar");
        
        }
    
       public void VerPaquetes(Contratable plan){
        
           Contratable p1,p2,p3,p4,p5,p6,p7;
           if(plan.getTipo().equals("Postpago")){
               p1 = new PegOtr1500(plan);
               p2 = new MixPls(plan);
               p4 = new MixPls(p1);
            }else{
               p1 = new PegOtr30(plan);
               p2 = new Moc200(plan);
               p4 = new Moc200(p1);
            }
            
            p3 = new Msm800(plan);     
            p5 = new Msm800(p1); 
            p6 = new Msm800(p2);   
            p7 = new Msm800(p4);   
            System.out.println(
                "Lista de paquetes que puede agregar a su plan \n opcion   Descripcion \n"
               +"\n 1  "+ p1.getNombre() +"  por un costo de: Bs "+p1.getTarifa()
               +"\n 2  "+ p2.getNombre() +"  por un costo de: Bs "+p2.getTarifa()   
               +"\n 3  "+ p3.getNombre() +"  por un costo de: Bs "+p3.getTarifa()
               +"\n 4  "+ p4.getNombre() +"  por un costo de: Bs "+p4.getTarifa()  
               +"\n 5  "+ p5.getNombre() +"  por un costo de: Bs "+p5.getTarifa()
               +"\n 6  "+ p6.getNombre() +"  por un costo de: Bs "+p6.getTarifa()
               +"\n 7  "+ p7.getNombre() +"  por un costo de: Bs "+p7.getTarifa() 
               +"\n 0 para cancelar"     
               );
            System.out.println("\n Elija la opcion del plan que desea activar");
            
       }
        
    
       public void ContratarServicio() throws IOException{
       
          BufferedReader ln = new BufferedReader(new InputStreamReader(System.in));
          boolean salir =false;
          int cop=0;
           do{
           System.out.println("Por favor ingrese el codigo de su producto");
          
           String prd = ln.readLine();
           
           if (prd.matches("[0-9]*")){
              cop = Integer.parseInt(prd);
             salir = true;
           }else{
           System.out.println("El codigo solo puede tener numeros");
           }
           
               
           }while(!salir);
           
           VerPlanes();
           salir =false;
           int op2=0;
          do{
           String op = ln.readLine();
           if (op.matches("[1-2]")){
              op2= Integer.parseInt(op);
              salir=true;
           }else{
           System.out.println("Solo puede elegir las opciones 1 o 2.");
           }
           
               
           }while(!salir);
          
         Contratable plan= null;
         int codp = 0;       // codigo del plan
         String tipo = ""; //tipo de plan
          if(op2==1){
            plan = new MOCEL2000();
            MOCEL2000 aux = new MOCEL2000();
            codp = aux.getCodigo();
            tipo = aux.getTipo();
          }
          
          if(op2==2){
            plan   = new MIXTOPLUS();
            MIXTOPLUS aux2  = new MIXTOPLUS();
            codp = aux2.getCodigo();
            tipo = aux2.getTipo();
          }
      
  
     ControladorServiciosOfrecidos cc= new ControladorServiciosOfrecidos();
     // asociamos el plan al producto
     if(cc.modificarProducto(cop,codp))
         System.out.println("El plan fue afiliado a su telefono");
       
        System.out.println("Si desea afiliar algun paquete adicional a su plan"
                + " por favor marque 1 en caso contrario marque 2.");
   
        salir=false;   
           do{
           String op = ln.readLine();
           if (op.matches("[1-2]")){
              op2= Integer.parseInt(op);
              salir=true;
           }else{
           System.out.println("Solo puede elegir las opciones 1 o 2.");
           }
               
           }while(!salir);
      
        if(op2==1){
           
            VerPaquetes(plan);
            int tepm=0;
            do{
           String op = ln.readLine();
           if (op.matches("[0-7]")){
              tepm= Integer.parseInt(op);
              salir=true;
           }else{
           System.out.println("Solo puede elegir las opciones 0 al 7.");
           }
               
           }while(!salir);
            
           Contratable p1,p2,p3;
           int p1co;
           int p2co;
           int p3co;
           if(tipo.equals("Postpago")){
               p1 = new PegOtr1500(plan);
               p1co = 1354;
               p2 = new MixPls(plan);
               p2co = 2781;
               
            }else{
               p1 = new PegOtr30(plan);
               p1co = 1546;
               p2 = new Moc200(plan);
               p2co = 3248;
            }
            
            p3 = new Msm800(plan);   
            p3co = 2456;  
            
            Afiliaciones af = new Afiliaciones();
            switch(tepm){
            case 0 :
                System.out.println("hasta luego");
                break;
            case 1:
                if(af.afiliarPaquete(codp,p1co,p1.getTipo()))
                    System.out.println("Paquete afiliado, hasta luego");
                
                break;
                
            case 2:
                if(af.afiliarPaquete(codp,p2co,p2.getTipo()))
                    System.out.println("Paquete afiliado, hasta luego");
                
                break;    
            
            case 3:
                if(af.afiliarPaquete(codp,p3co,p3.getTipo()))
                    System.out.println("Paquete afiliado, hasta luego");
                
                break;
             case 4:
                if(af.afiliarPaquete(codp,p1co,p1.getTipo()) &&af.afiliarPaquete(codp,p2co,p2.getTipo()) )
                    System.out.println("Paquetes afiliado, hasta luego");
                
                break;
            
             case 5:
                if(af.afiliarPaquete(codp,p1co,p1.getTipo()) &&af.afiliarPaquete(codp,p3co,p3.getTipo()) )
                    System.out.println("Paquetes afiliado, hasta luego");
                
                break;
                 
               case 6:
                if(af.afiliarPaquete(codp,p2co,p2.getTipo()) &&af.afiliarPaquete(codp,p3co,p3.getTipo()) )
                    System.out.println("Paquetes afiliado, hasta luego");
                
                break;  
                
                case 7:
                if(af.afiliarPaquete(codp,p1co,p1.getTipo())&&af.afiliarPaquete(codp,p2co,p2.getTipo()) &&af.afiliarPaquete(codp,p3co,p3.getTipo()) )
                    System.out.println("Paquetes afiliado, hasta luego");
                
                break; 
                         
            }
            
            
            
        }else{
            System.out.println("hasta luego");
        }
        
       
       }
   


}
    
    
