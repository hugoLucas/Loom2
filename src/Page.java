import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Hugo on 3/23/2017.
 */
public class Page extends ProgramSection {

    private String pageTitle;
    private String pageIdentifier;
    private String pageText;

    private ArrayList<String> optionText;
    private ArrayList<String> optionIdentifier;
    private ArrayList<IfStatement> pageIfStatementMap;

    private boolean hasIfStatement;
    private boolean duplicateIdentifiers;

    public Page(){
        super();
        this.pageTitle = null;
        this.pageIdentifier = null;
        this.pageText = null;

        this.optionText = new ArrayList<>();
        this.optionIdentifier = new ArrayList<>();
        this.pageIfStatementMap = new ArrayList<>();
        this.duplicateIdentifiers = false;
        this.hasIfStatement = false;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getSectionIdentifier() {
        return pageIdentifier;
    }

    @Override
    boolean containsComponentIdentifier(String identifier) {
        return pageIdentifier.equals(identifier) || this.optionIdentifier.contains(identifier);
    }

    public void setPageIdentifier(String pageIdentifier) {
        this.pageIdentifier = pageIdentifier;
    }

    public String getPageText() {
        return pageText;
    }

    public void setPageText(String pageText) {
        this.pageText = pageText;
    }

    public void addPageOptions(String optionText, String optionIdentifier) {
        this.optionText.add(optionText);

        if(!this.optionIdentifier.contains(optionIdentifier) && !this.pageIdentifier.equals(optionIdentifier))
            this.optionIdentifier.add(optionIdentifier);
        else
            this.duplicateIdentifiers = true;
    }

    public boolean hasDuplicateIdentifiers(){
        return this.duplicateIdentifiers;
    }

    public boolean isComplete(){
        boolean a = this.getPageText() != null;
        boolean b = this.getPageTitle() != null;
        boolean c = this.getSectionIdentifier() != null;

        return (a && b && c);
    }

    @Override
    public ArrayList<String> getAllIdentifiers() {
        ArrayList<String> identifierList = new ArrayList<>(1 + this.optionIdentifier.size());
        identifierList.add(this.pageIdentifier);
        identifierList.addAll(this.optionIdentifier);
        return identifierList;
    }

    @Override
    public ArrayList<String> getAllVariableAssignments() {
        return null;
    }

    @Override
    public ArrayList<String> getAllSectionalReferences() {
        return null;
    }

    @Override
    public ArrayList<Reference> getAllReferences() {
        ArrayList<Reference> referenceList = new ArrayList<>();
        if(this.hasIfStatement)
            referenceList.addAll(this.pageIfStatementMap.stream()
                    .map(IfStatement::getIfStatementReference)
                    .collect(Collectors.toList()));
        return referenceList;
    }


    public ArrayList<String> getPageOptions(){
        return this.optionIdentifier;
    }

    public boolean addIfStatement(IfStatement ifStmt){
        for(IfStatement is : this.pageIfStatementMap)
            if(is.equals(ifStmt))
                return false;

        this.pageIfStatementMap.add(ifStmt);

        if(!this.hasIfStatement)
            this.hasIfStatement = true;

        return true;
    }

    public ArrayList<String> getPageOptionText(){
        return this.optionText;
    }

    @Override
    public String toString(){
        return "PAGE";
    }
}