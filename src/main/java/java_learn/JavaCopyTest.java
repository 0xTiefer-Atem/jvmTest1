package java_learn;

public class JavaCopyTest {
    public static void main(String[] args) {
        MyString m = new MyString();
        MyString n = m;
        System.out.println(n.getStr());
        m.setStr("qqqqq");
        System.out.println(n.getStr());
        System.out.println(m.equals(n));
    }
}

class MyString{
    private String str = "aaaaa";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
