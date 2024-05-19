package model;

public interface IPaintable //visitable
{ 
	void accept(IPaintVisitor visitor);

}
