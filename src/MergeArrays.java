import java.io.File;
import java.util.Comparator;
import java.util.List;

public class MergeArrays {
    public static void main(String[] args) {
        FilesProcessing arguments = new FilesProcessing(args);
        Comparator<String> cmp = arguments.getComporator();
        List<File> filelist = arguments.getFilelist();
        File outputfile = arguments.getOutputfile();
        FilesProcessing.mergeFiles(filelist, outputfile, cmp, arguments.getSortmode(), arguments.getDatatype());
    }
}
