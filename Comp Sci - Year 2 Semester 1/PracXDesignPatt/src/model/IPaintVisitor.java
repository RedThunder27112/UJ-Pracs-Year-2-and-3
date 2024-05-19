package model;

/**
 * IPaintVisitor, visitor class
 * @author Ariel Sischy, 221003350
 * @Version P08
 */

public interface IPaintVisitor //visitor
{
	void visit(Circle circle);
	void visit(Rectangle rectangle);

}
