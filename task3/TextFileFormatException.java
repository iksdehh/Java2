package Java2.task3;

import java.io.IOException;

public class TextFileFormatException extends RuntimeException
{
    public TextFileFormatException(String fehlermeldung)
    {
        super(fehlermeldung);
    }
}
