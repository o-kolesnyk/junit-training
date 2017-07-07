package frameworks.homework;

import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.annotation.Annotation;

public class RunAgainRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description desc) {
        return new RunAgainStatement(base, desc);
    }

    public class RunAgainStatement extends Statement {

        private final Statement base;
        private Description desc;

        public RunAgainStatement(Statement base, Description desc) {
            this.base = base;
            this.desc = desc;
        }

        @Override
        public void evaluate() throws Throwable {
            Unstable unstable = desc.getAnnotation(Unstable.class);
            if (unstable != null) {
                boolean isPassed = false;
                int i = 0;
                while (!isPassed && i < unstable.value() - 1) {
                    try {
                        base.evaluate();
                        isPassed = true;
                    } catch (Throwable t) {
                        i++;
                    }
                }
                if (!isPassed) {
                    base.evaluate();
                }
            }

        }

    }


}
