// 예제 9-28 JedisHelper

... package, import 생략 ...

public class JedisHelper {
    protected static final String REDIS_HOST = "127.0.0.1";
    protected static final int REDIS_PORT = 6379;
    private final Set<Jedis> connectionList = new HashSet<Jedis>();
    private final JedisPool pool;

    private JedisHelper() {
	GenericObjectPoolConfig = new GenericObjectPoolConfig();
	config.setMaxTotal(20);
	config.setBlockWhenExhausted(true);

	this.pool = new JedisPool(config, REDIS_HOST, REDIS_PORT);
    }

    private static class LazyHolder {
	@SupressWarnings("synthetic-access")
	private static final JedisHelper INSTANCE = new JedisHelper();
    }

    @SupressWarnings("synthetic-access")
    public static JedisHelper getInstance() {
	return LazyHolder.INSTANCE;
    }

    final public Jedis getConnection() {
	Jedis jedis = this.pool.getResource();
	this.connectionList.add(jedis);

	return jedis;
    }

    final public void returnResource(Jedis jedis) {
	this.pool.returnResource(jedis);
    }

    final public void destroyPool() {
	Iterator<Jedis> jedisList = this.connectionList.iterator();
	while (jedisList.hasNext()) {
	    Jedis jedis = jedisList.next();
	    this.pool.returnResource(jedis);
	}

	this.pool.destroy();
    }
}
