package mysqlite;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String name;
    private List<Table> tableList = new ArrayList<>();

    public Database( String name ){ this.name = name; }

    public void addTable( Table table){
        this.tableList.add( table );
    }
    public String getName(){return this.name;}

    public String getCreateTable( String tableName ){
        Table table = null;

        for( Table t : tableList){
            if( t.equals( tableName )){
                table = t;
                break;
            }
        }

        return table.getCreateTable();
    }

    public List<Table> getTableList(){return tableList;}
}
