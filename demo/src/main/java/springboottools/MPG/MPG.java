package springboottools.MPG;

/**
 * mybatis-plus逆向工程
 */
public class MPG {

    public static void main(String[] args) {
        String projectPath = MPG.class.getResource("/").getPath().replace("/target/classes", "").replaceFirst("/","");
        MPGeneratorConfig config = new MPGeneratorConfig();
        // 指定表名即可
        config.setTables(new String[]{"bop_role"});
        config.setProjectPath(projectPath);
        config.setBasePackage(MPG.class.getPackage().getName());
        final MPGenerator mpGenerator = new MPGenerator(config);
        mpGenerator.execute();
    }


}
