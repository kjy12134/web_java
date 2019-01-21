package sinc.ctrl.view;

public class View {
	// 분기해야 할 페이지 정보, 분기 방식을 지정해주는 속성을 담고 있어야 한다.
	private String path;
	private boolean isSend; // true : dispatcher, false : redirect

	public View() {
	}

	public View(String path, boolean isSend) {
		this.path = path;
		this.isSend = isSend;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isSend() {
		return isSend;
	}

	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}

}
