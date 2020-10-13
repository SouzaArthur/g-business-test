package com.g.test.enums;

public enum ProdutoEnum {
	
	MAVO(1),
	N19H(2),
	HUCK(3),
	DFAU(4);
	
	private int code;
	
	private ProdutoEnum(Integer code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static ProdutoEnum toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(ProdutoEnum x: ProdutoEnum.values()) {
			if(code == x.getCode()) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código não encontrado.");
		
		
	}
}
