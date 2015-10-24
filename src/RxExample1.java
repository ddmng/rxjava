import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import com.github.davidmoten.rx.FileObservable;

class RxExample1 {
    public static void sayHello(String[] names) {
        Observable.from(names).subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("Hello " + s + "!");
                    }

                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable i) {
                        System.out.println("Errore");
                    }

                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Completo");
                    }

                });
    }


    public static void sayHelloAlways(String... names) {
        Observable.from(names).repeat(5).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }

        });
    }

    public static void tailSyslog() {
        Observable<String> syslog =
                FileObservable.tailer()
                        .file("/var/log/syslog")
                        .tailText();

        syslog.forEach(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Riga di log: " + s);
            }
        });
    }

    public static void main(String args[]) {

        sayHello(new String[]{"uno", "due", "tre"});
        sayHelloAlways("one", "two", "three");
        tailSyslog();

    }

}
