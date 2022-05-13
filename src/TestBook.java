interface Book{
    String getCategory();

}
abstract class ScienceBook implements Book{
    String bookName;
    String category;
    public ScienceBook(String bookName)
    {
        this.bookName=bookName;
        this.category=getCategory();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
class MathBook extends ScienceBook{

    public MathBook(String bookName) {
        super(bookName);
    }

    @Override
    public String getCategory() {
        return category="Science/Math";
    }
}
abstract class ComputerBook extends ScienceBook{

    public ComputerBook(String bookName) {
        super(bookName);
    }
}
class SoftwareBook extends ComputerBook
{
    public SoftwareBook(String bookName)
     {
         super(bookName);
     }

    @Override
    public String getCategory() {
        return category="Science/Computer/software";
    }
}
class HardwareBook extends ComputerBook
{

    public HardwareBook(String bookName) {
        super(bookName);
    }

    @Override
    public String getCategory() {
        return category="Science/Computer/hardware";
    }
}
class ProgramDesignBook extends SoftwareBook
{

    public ProgramDesignBook(String bookName) {
        super(bookName);
    }
}
class ProgramDevelopmentBook extends SoftwareBook
{

    public ProgramDevelopmentBook(String bookName) {
        super(bookName);
    }
}

public class TestBook {
    public static void main(String[] args) {
        HardwareBook book=new HardwareBook("计算机硬件技术基础");
        System.out.println(book);
    }
}
