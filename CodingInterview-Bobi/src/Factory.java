public class Factory {

	public static Factory getInstance(int type) {
		if (type == 1) {
			return new A_Type();
		} else {
			return new B_Type();
		}
	}
}

class A_Type extends Factory {

}

class B_Type extends Factory {

}