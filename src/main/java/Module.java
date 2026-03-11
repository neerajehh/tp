public class Module {
    private String moduleCode;
    private String moduleName;

    public Module(String moduleCode, String moduleName) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return moduleCode + " " + moduleName;
     }
}
