package cn.tedu.cloudnote.util;

public class NoteException extends RuntimeException {
	public NoteException(String m,Throwable e){
		super(m, e);
	}
}
