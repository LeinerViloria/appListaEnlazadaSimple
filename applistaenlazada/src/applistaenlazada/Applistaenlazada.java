/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applistaenlazada;

import javax.swing.JOptionPane;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 *
 * @author 1005640772
 */
public class Applistaenlazada {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaEnlazada list = new listaEnlazada();
        nodo aux;
        
        int option = optionsMenu();
        do {                     
            switch(option){
                case 1:
                    aux = new nodo();
                    JOptionPane.showMessageDialog(null, "ESTUDIANTE #"+(list.getNodesLenght()+1));
                    fill(aux);
                    list.addNodeToEnd(aux);
                    option = optionsMenu();
                    break;
                case 2:
                    //Hay informacion en la lista
                    if(list.getHead()!=null){
                        nodeList(list.getHead());
                    }else{
                        JOptionPane.showMessageDialog(null, "La lista esta vacia");
                    }
                    option = optionsMenu();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Numero de estudiantes en la lista:\n"+
                            list.getNodesLenght());
                    option = optionsMenu();
                    break;
                case 4:
                    int code = Integer.parseInt(validString("Digite su codigo:", true, false));
                    aux = list.searchNode(code);
                    if(aux != null ){
                        show(aux);
                    }else{
                        JOptionPane.showMessageDialog(null,"Informacion no encontrada");
                    }
                    option = optionsMenu();
                    break;
                case 5:
                    int code2 = Integer.parseInt(validString("Ingrese el codigo del estudiante a eliminar:", true, false));
                    aux = list.searchNode(code2);
                    
                    if(aux!=null){
                        list.deleteNode(aux);
                        JOptionPane.showMessageDialog(null, "La informacion se elimino exitosamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "La informacion no fue encontrada");
                    }
                    option = optionsMenu();
                    break;
                case 6:
                    //Promedio general y nota maxima
                    float generalNote = list.generalNote();
                    float hightestMarks = list.theHighestMark();
                    
                    JOptionPane.showMessageDialog(null, "El promedio general es: "+generalNote+"\n"+
                                                        "La nota maxima es: "+hightestMarks);
                    option = optionsMenu();
                    break;
                case 7:
                    list.clean();
                    option = optionsMenu();
                    break;
                case 8:
                    aux = new nodo();
                    fill(aux);
                    list.putNodeInFirstPlace(aux);
                    option = optionsMenu();
                    break;
                case 9:
                    int codeToFind = Integer.parseInt(validString("Ingrese el codigo del estudiante en el que se quiere ubicar", true, false));
                    
                    nodo nodeToWork = list.searchNode(codeToFind);
                    
                    if(nodeToWork!=null){
                        nodo newNode = new nodo();
                        int where = Integer.parseInt(validString("1. Agregar antes\n"
                                                                + "2. Agregar despues\n"
                                                                + "3. Atras", true, false));
                        if(where == 1){
                            fill(newNode);
                            list.putNodeInTheMiddle(nodeToWork, newNode, false);
                        }else if(where == 2){
                            fill(newNode);
                            list.putNodeInTheMiddle(nodeToWork, newNode, true);
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Estudiante no encontrado");
                    }
                    
                    option = optionsMenu();
                    break;
                case 10:
                    JOptionPane.showMessageDialog(null, "Se ordenara de menor a mayor");
                    list.bubbleMethod();
                    option = optionsMenu();
                    break;
                case 11:
                    JOptionPane.showMessageDialog(null, "Que tenga buen dia");
                    break;
                default:
                    option = optionsMenu();
                    break;
            }
            
        } while (option!=11);
    }
    
    //Metodo para llenar la lista enlazada
    public static void fill(nodo node){
        int code = Integer.parseInt(validString("Ingrese el codigo", true, false));
        String name = validString("Ingrese el nombre del estudiante", false, false);
        float mark1 = fillMark("primera");
        float mark2 = fillMark("segunda");
        float mark3 = fillMark("tercera");
        
        node.setCode(code);
        node.setName(name);
        node.setMark1(mark1);
        node.setMark2(mark2);
        node.setMark3(mark3);
        
    }
    
    private static float fillMark(final String message){
        //Lo inicio en negativo ya que se puede ingresar una nota cero
        float mark=-1;
        
        do {            
            mark = Float.parseFloat(validString("Digite la "+message+" nota", true, true));
        } while (mark<0 || mark>5);
        
        return mark;
    }
    
    /**
     *
     * @param message, es el mensaje que quiera mostrar
     * @param numericValidation, para distinguir entre String y variables 
     *                           numericas
     * @param isFloatNumber, para saber si es un numero flotante y se 
     *                       necesite su punto decimal
     * @return
     */
    public static String validString(final String message, final boolean numericValidation, final boolean isFloatNumber){
        String value=null;
        boolean flag; //Por si necesita validacion numerica
        do {            
            value = JOptionPane.showInputDialog(message);
            //La funcion trim limpia los espacios a la izquierda y derecha
            value = value.trim();
            //Si necesita validacion numerica verifica que lo ingresado sea un numero
            flag = numericValidation ? isNumeric(value, isFloatNumber) : true;
        } while (value.contentEquals("") || !flag);
        
        return value;
    }
    
    //Se cogio la funcion de la libreria StringUtils
    //Se adaptará para que admita flotantes
    public static boolean isNumeric(final CharSequence cs, boolean isFloatNumber) {
       if (isEmpty(cs)) {
           return false;
       }
       final int sz = cs.length();
       
       String ourCharacter;//Se lo agregue
       boolean decimalFound = false;//Se lo agregue
       //Le agregue isFloatNumber
       
       for (int i = 0; i < sz; i++) {
           //Creo ourCharacter para tener acceso a metodos de String
            ourCharacter = Character.toString(cs.charAt(i));//Se lo agregue
            if (!Character.isDigit(cs.charAt(i))) {
                //Le agregue desde aqui
                if(ourCharacter.contentEquals(".") && isFloatNumber && !decimalFound){
                    decimalFound = true;
                }else{
                    return false;
                }
                //Hasta aqui
            }
        }

       return true;
   }
    
    public static void show(nodo node){
        String nodeData = "";
        
        nodeData = nodeData + String.valueOf("Codigo: "+node.getCode()+"\n"+
                "Nombre: "+node.getName()+"\n"+
                "Sus notas fueron: "+
                node.getMark1()+", "+
                node.getMark2()+" y "+
                node.getMark3()+"\n"+
                "Su definitiva fue: "+node.finalMark());
        
        JOptionPane.showMessageDialog(null, "-------------------------------INFORMACION DE LOS ESTUDIANTES-------------------------------\n" + nodeData);
    }
    
    //Metodo para listar uno a uno los nodos de la lista
    public static void nodeList(nodo node){
        nodo temp = node;
        while (temp != null) { show(temp); temp = temp.getNext(); }   
    }
    
    public static int optionsMenu(){
        int option = 0;
        
        do {            
            option = Integer.parseInt(validString("Seleccione una opcion del menu:\n"+
                                                  "1. Agregar un estudiante a la lista\n"+
                                                  "2. Mostrar todos los estudiantes del curso\n"+
                                                  "3. Cantidad de estudiantes existentes en la lista\n"+
                                                  "4. Buscar la informacion de un estudiante\n"+
                                                  "5. Eliminar a un estudiante de la lista\n"+
                                                  "6. Informe de promedio general y nota máxima\n"+
                                                  "7. Borrar la lista de estudiantes\n"+
                                                  "8. Agregar estudiante de cabeza\n"+
                                                  "9. Agregar estudiante en el medio\n"+
                                                  "10. Ordenar nodos\n"+
                                                  "11. Salir", true, false));
        } while (option < 1 || option>11);
        
        return option;
    }
    
}
