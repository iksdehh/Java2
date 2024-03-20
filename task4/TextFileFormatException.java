package task4;

public class TextFileFormatException extends RuntimeException
{
    public TextFileFormatException(String fehlermeldung)
    {
        super(fehlermeldung);
    }
}
