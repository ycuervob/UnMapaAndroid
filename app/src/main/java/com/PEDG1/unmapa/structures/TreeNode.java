
package com.PEDG1.unmapa.structures;

import com.PEDG1.unmapa.data.Comment;

/**
 * Permite la creacion de un nodo generico con parametro T, esta implementacion cuenta con apuntadores a los hijos
 * izquierdos y derechos del nodo así como a su contenido y a su padre.
 * @author yeison
 * @param <T> Tipo de dato que extiende de la clase locacion
 */


public class TreeNode<T extends Comment> {
    public T key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parentNode;
    public int height;
 

    public TreeNode(T key, TreeNode left, TreeNode right, TreeNode pareNode) {
        this(key, pareNode);
        this.left = left;
        this.right = right;
        height = 1;
    }
    
    public TreeNode(T key, TreeNode pareNode) {
        this(key);
        this.parentNode = pareNode;
        left = null;
        right = null;
        height = 1;
    }

    public TreeNode(T key) {
        this.key = key;
        left = null;
        right = null;
        parentNode = null;
        height = 1;
        
    }

    
    
    
    
}
