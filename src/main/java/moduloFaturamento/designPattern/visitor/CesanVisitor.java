package moduloFaturamento.designPattern.visitor;


public interface CesanVisitor<V> {

	void visit(V visitee);
}
