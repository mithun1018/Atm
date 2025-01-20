package ATM_Console_Application.Notes;

public class Notes implements Cloneable{
    private   String notes;
    private  long count;
    protected Notes(String notes,long count){
        this.notes=notes;
        this.count=count;
    }
    public  void setNotes(String notes){
        this.notes=notes;
    }

    public  void setCount(Long count){
        this.count=count;
    }
    public  String getNotes(){
        return notes;
    }
    public  long getCount(){
        return count;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
