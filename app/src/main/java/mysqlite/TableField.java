package mysqlite;

public class TableField {
    private String name;
    private FieldType type;
    private Integer size;
    private Boolean primarykey;

    public TableField( String name , FieldType type , Integer size , Boolean primarykey ){
        this.name = name;
        this.type = type;
        this.size = size;
        this.primarykey = primarykey;
    }

    public String getName(){return name;}
    public String getCreateTable(){

        String sql = name + " " + type.toString() + "(" + size.toString() + ")";
        return sql;
    }
    public FieldType getFieldType(){return type;}
    public Boolean equals( String name ){
        return this.name.equals(name);

    }

}