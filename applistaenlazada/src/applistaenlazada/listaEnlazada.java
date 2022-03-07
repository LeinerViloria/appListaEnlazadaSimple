/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applistaenlazada;

/**
 *
 * @author 1005640772
 */
public class listaEnlazada {
    private nodo head;//Controla el primer nodo de la lista

    public listaEnlazada() {
        this.head=null;
    }

    public nodo getHead() {
        return head;
    }

    public void setHead(nodo head) {
        this.head = head;
    }
    
    public nodo lastNode(){
        nodo temp = this.head;
        
        while(temp!=null){
            //temp = (temp.getNext() == null) ? null : temp.getNext();
            if (temp.getNext() == null) {
                break;
            }else{
                temp=temp.getNext();
            }
        }
        
        return temp;
    }
    
    public void putNodeInFirstPlace(nodo newNode){
        if(this.head==null){
            this.setHead(newNode);//Enlace
        }else{
            nodo temp = this.head;
            nodo aux = newNode;
            aux.setNext(temp);
            this.setHead(aux);
        }
    }
    
    public void putNodeInTheMiddle(nodo nodeToWork, nodo newNode, boolean next){
        nodo temp = this.head;
        nodo aux;
        
        while(temp!=null){
            if(!next){
                if(temp.getNext()==nodeToWork){
                    aux = temp.getNext();
                    newNode.setNext(aux);
                    temp.setNext(newNode);
                    break;
                }
            }else{
                if(temp == nodeToWork){
                    aux = nodeToWork.getNext();
                    newNode.setNext(aux);
                    temp.setNext(newNode);
                    break;
                }
            }
            temp = temp.getNext();
        }
    }
    
    /*
    Metodo para recorrer la lista y contar cuántos nodos hay almacenados
    */
    
    public int getNodesLenght(){
        nodo temp = this.head;
        int count = 0;
        
        while(temp!=null){
            count++;
            temp = temp.getNext();
        }
        
        return count;
    }
    /*
    Metodo para agregar un nuevo nodo en la lista
    Principio, intermedio y final
    
    Se va a hacer por final
    */
    public void addNodeToEnd(nodo newNode){
        if(this.head==null){
            this.setHead(newNode);//Enlace
        }else{
            lastNode().setNext(newNode);
        }
    }
    
    /*
    Metodo de busqueda de un nodo dentro de la lista,
    pasando como parametro el codigo del estudiante
    */
    
    public nodo searchNode(int code){
        nodo temp = this.head;
        
        while(temp!=null){
            if (temp.getCode()==code) {
                break;
            } else {
                temp = temp.getNext();
            }
        }
        
        return temp;
    }
    
    //Eliminar el nodo de la lista, pasando como parametro
    //el nodo a eliminar
    
    public void deleteNode(nodo nodeToDelete){
        nodo prev ;
        if(nodeToDelete==this.head){
            this.head=this.head.getNext();
        }else{
            prev = this.head;
            while (prev.getNext()!= nodeToDelete) {                
                prev=prev.getNext();
            }
            prev.setNext(nodeToDelete.getNext());
        }
        
        nodeToDelete.setNext(null);
        
    }
    
    /*
    Implementar metodo que calcula el promedio de las notas
    */
    public float generalNote(){
        int cant = 0;
        float add = 0;
        nodo temp = this.head;
        
        while(temp!=null){
            cant++;
            add += temp.finalMark();
            temp=temp.getNext();
        }
        
        if(cant > 0){
            return add/cant;
        }else{
            return 0;
        }
        
    }
    
    public float theHighestMark(){
        float finalMark;
        float maxMark=0;
        nodo temp = this.head;
        
        while(temp!=null){
            finalMark=temp.finalMark();
            
            if(finalMark>maxMark){
                maxMark = finalMark;
            }
            
            temp = temp.getNext();
        }
        
        return maxMark;
    }
    
    public void clean(){
        while(this.head!=null){
            deleteNode(this.head);
        }
    }
    
    //Se coge el metodo burbuja y se acomoda a lo requerido
    public void bubbleMethod(){
        nodo temp;
        int i=0;
        nodo aux;
            
        while(i<this.getNodesLenght()){
            temp = this.head;
            
            while(temp!=null){
                
                if(temp.getNext()!=null){
                    if(temp.getCode()>temp.getNext().getCode()){
                        aux = temp;
                        temp = temp.getNext();
                        temp.setNext(aux);
                    }
                }
            
                temp=temp.getNext();
            }

            i++;
        }
        
        System.out.println("Termino la ejecucion");
        
    }
}