package dev.juanvega;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DummyTest {

    @Test
    public void basicTest() {
        Assertions.assertThat(new Dummy(false)).isEqualTo(new Dummy(true));
    }

}
