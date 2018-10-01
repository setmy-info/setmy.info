package ee.pub.hibernate.service;

import ee.pub.hibernate.dao.WorldDao;
import ee.pub.hibernate.model.World;

public class WorldService {

    private WorldDao worldDao;

    public World saveWorld(final World world) {
        return this.worldDao.save(world);
    }

    public World findWorld() {
        final Long worldId = 1L;
        return worldDao.find(worldId);
    }

    public WorldDao getWorldDao() {
        return worldDao;
    }

    public void setWorldDao(WorldDao worldDao) {
        this.worldDao = worldDao;
    }
}
