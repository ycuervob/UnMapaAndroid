package com.PEDG1.unmapa.data;

/**
 *
 * @author yeison
 */
public class Comment implements Comparable<Comment>{
    private double NunComment;
    private double buildingComment;
    private String dateComment;
    private String Comment;

    public Comment(double NunComment, double buildingComment, String dateComment, String Comment) {
        this.NunComment = NunComment;
        this.buildingComment = buildingComment;
        this.dateComment = dateComment;
        this.Comment = Comment;
    }

    public double getNunComment() {
        return NunComment;
    }

    public void setNunComment(double NunComment) {
        this.NunComment = NunComment;
    }

    public double getBuildingComment() {
        return buildingComment;
    }

    public void setBuildingComment(double buildingComment) {
        this.buildingComment = buildingComment;
    }

    public String getDateComment() {
        return dateComment;
    }

    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
    
    @Override
    public String toString(){
        return this.NunComment+";"+this.buildingComment+";"+this.dateComment+";"+this.Comment;
    }

    @Override
    public int compareTo(Comment t) {
        int retorno = 0;
       
        if(t.getNunComment() == this.NunComment){
            retorno = 0;
        }else if(t.getNunComment() < this.NunComment){
            retorno = -1;
        }else if(t.getNunComment() > this.NunComment){
            retorno = 1;
        }
        
        return retorno;
    }
    
    
}
