package dto;

public class Material {
    private int id;
    private int projectId;
    private int accountId;
    private String sampleName;
    private String csvFileName;
    private String csvFilePath;
    private String pptxFileName;
    private String pptxFilePath;

    public Material() {}

    // 업로드용 생성자
    public Material(int projectId, int accountId, String sampleName, 
                    String csvFileName, String csvFilePath, 
                    String pptxFileName, String pptxFilePath) {
        this.projectId = projectId;
        this.accountId = accountId;
        this.sampleName = sampleName;
        this.csvFileName = csvFileName;
        this.csvFilePath = csvFilePath;
        this.pptxFileName = pptxFileName;
        this.pptxFilePath = pptxFilePath;
    }

    // Getter & Setter (필수)
    public int getProjectId() { return projectId; }
    public int getAccountId() { return accountId; }
    public String getSampleName() { return sampleName; }
    public String getCsvFileName() { return csvFileName; }
    public String getCsvFilePath() { return csvFilePath; }
    public String getPptxFileName() { return pptxFileName; }
    public String getPptxFilePath() { return pptxFilePath; }
}