package businessLogic;

import java.util.List;

public interface BusinessLogic<T> {
    /**
     * @post @result != null
     */
    List<T> findAll();

    //todo interfata/interfetele pt disign by contract
}
