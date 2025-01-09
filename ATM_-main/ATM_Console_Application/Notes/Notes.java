package ATM_Console_Application.Notes;

public class Notes {
    private  String notes;
    private  int count;
    protected Notes(String notes,int count){
        this.notes=notes;
        this.count=count;
    }
    public  void setNotes(String notes){
        this.notes=notes;
    }

    public  void setCount(int count){
        this.count=count;
    }
    public String getNotes(){
        return notes;
    }
    public int getCount(){
        return count;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
