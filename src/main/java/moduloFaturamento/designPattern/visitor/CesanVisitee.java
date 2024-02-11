package moduloFaturamento.designPattern.visitor;


public interface CesanVisitee<V> {
	
	void accept(CesanVisitor<V> visitor);
}
