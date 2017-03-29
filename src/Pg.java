import java.util.InputMismatchException;

/**
 * Created by hugoj on 3/27/2017.
 */
public class Pg extends Definition {

    public static String PG = "PG";

    public Pg (String var, boolean beginningPage, boolean endPage){
        super();
        if(beginningPage)
            this.setDefinitionTimeIndent("START");
        else if(endPage)
            this.setDefinitionTimeIndent("END");

        this.setDefinitionTargetNONWSSTR(var);
    }

    public Pg (ComponentID compId, boolean beginningPage, boolean endPage){
        super();
        if(beginningPage)
            this.setDefinitionTimeIndent("START");
        else if(endPage)
            this.setDefinitionTimeIndent("END");

        this.setDefinitionTargetComponentId(compId.getComponentID());
    }

    public String returnPgTarget(){
        if(this.getDefinitionTargetComponentId() != null)
            return this.getDefinitionTargetComponentId();
        else
            return this.getDefinitionTargetNONWSSTR();
    }

    @Override
    public String getStatementType() {
        return PG;
    }
}
