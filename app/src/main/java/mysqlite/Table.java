package mysqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public abstract class Table {
    protected String name;
    protected List<TableField> fieldList = new ArrayList<>();
    protected List<String[]> fieldValues = new ArrayList<>();

    public Table( String name ){
        this.name = name;
        setFieldList();
    }

    public void addField( TableField field ){
        fieldList.add( field );
    }

    public String getCreateTable(){
        String sql = "create table " + this.name + "(";

        for( int i = 0 ; i < fieldList.size() ; i++ ){
            if( i == 0 ){
                sql = sql + fieldList.get(i).getCreateTable();
            }else{
                sql = sql + "," + fieldList.get(i).getCreateTable();
            }
        }

        return sql + ");";

    }
    public String getDeleteTable(){
        return "delete from " + this.name + ";";
    }
    public String getDropTable(){
        return "drop table " + name + ";";
    }
    public List<String> getInsertTable(){
        List<String> list = new ArrayList<>();
        //バリューがなくなるまで
        for( String[] value : fieldValues ){
            String sql = "insert into @tbl(@fields) values (@values);";

            sql = sql.replace( "@tbl", name );

            String fields = "";
            String values = "";

            for( int i = 0 ; i < fieldList.size() ; i++ ){
                if( i == 0 ){
                    fields = fieldList.get(i).getName();
                    if( fieldList.get(i).getFieldType()==FieldType.INT){
                        values = value[i];
                    }else{
                        values = "'" + value[i] + "'";
                    }
                }else{
                    fields = fields + "," + fieldList.get(i).getName();
                    if( fieldList.get(i).getFieldType()==FieldType.INT ){
                        values = values + "," + value[i];
                    }else{
                        values = values + "," + "'" + value[i] + "'";
                    }
                }
            }
            sql = sql.replace("@fields",fields);
            sql = sql.replace("@values",values);
            list.add( sql );
        }

        return list;
    }
    public void setValue(String[] values){
        fieldValues.add( values );
    }

    public Boolean equals( String tableName ){
        return name.equals( tableName );

    }

    abstract public void setFieldList();
}