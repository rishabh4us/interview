public class Singleton {
	private static Singleton _instance;

	private Singleton() {
		_instance = null;
	}

	public static Singleton getInstance() {
		if(_instance == null) {
			_instance = new Singleton();
		}
		return _instance;
	}
}
