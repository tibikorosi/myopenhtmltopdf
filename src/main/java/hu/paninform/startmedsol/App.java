package hu.paninform.startmedsol;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder.FontStyle;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

/**
 * Hello world!
 */
public final class App {

    public static final String SOURCES = System.getProperty("user.dir").replace("\\", "/") + "/work/";
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Print started...");

        HtmlRenderer renderer = new HtmlRenderer();
        try (OutputStream os = new FileOutputStream(SOURCES + "output.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.useFont(new File(SOURCES + "NotoSerif-Regular.ttf"), "noto");
            builder.useFont(new File(SOURCES + "NotoSerif-Bold.ttf"), "noto", 700, FontStyle.NORMAL, /* subset: */ true);
            builder.useFont(new File(SOURCES + "NotoSerif-Italic.ttf"), "noto", 400, FontStyle.ITALIC, /* subset: */ true);
            builder.useFont(new File(SOURCES + "NotoSerif-BoldItalic.ttf"), "noto", 700, FontStyle.ITALIC, /* subset: */ true);
            builder.withHtmlContent(renderer.getInputData(), "file:///" + SOURCES);
            builder.toStream(os);
            builder.run();
        }
        System.out.println("Print finished");
    }
}
