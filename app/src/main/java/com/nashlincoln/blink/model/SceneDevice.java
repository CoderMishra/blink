package com.nashlincoln.blink.model;

import java.util.List;
import com.nashlincoln.blink.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import com.nashlincoln.blink.app.BlinkApp;
// KEEP INCLUDES END
/**
 * Entity mapped to table SCENE_DEVICE.
 */
public class SceneDevice {

    private Long id;
    private Long sceneId;
    private Long deviceId;
    private String attributableType;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient SceneDeviceDao myDao;

    private Scene scene;
    private Long scene__resolvedKey;

    private Device device;
    private Long device__resolvedKey;

    private List<Attribute> attributes;

    // KEEP FIELDS - put your custom fields here
    private static final String ATTRIBUTABLE_TYPE = "Scene";
    // KEEP FIELDS END

    public SceneDevice() {
    }

    public SceneDevice(Long id) {
        this.id = id;
    }

    public SceneDevice(Long id, Long sceneId, Long deviceId, String attributableType) {
        this.id = id;
        this.sceneId = sceneId;
        this.deviceId = deviceId;
        this.attributableType = attributableType;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSceneDeviceDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getAttributableType() {
        return attributableType;
    }

    public void setAttributableType(String attributableType) {
        this.attributableType = attributableType;
    }

    /** To-one relationship, resolved on first access. */
    public Scene getScene() {
        Long __key = this.sceneId;
        if (scene__resolvedKey == null || !scene__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SceneDao targetDao = daoSession.getSceneDao();
            Scene sceneNew = targetDao.load(__key);
            synchronized (this) {
                scene = sceneNew;
            	scene__resolvedKey = __key;
            }
        }
        return scene;
    }

    public void setScene(Scene scene) {
        synchronized (this) {
            this.scene = scene;
            sceneId = scene == null ? null : scene.getId();
            scene__resolvedKey = sceneId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Device getDevice() {
        Long __key = this.deviceId;
        if (device__resolvedKey == null || !device__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DeviceDao targetDao = daoSession.getDeviceDao();
            Device deviceNew = targetDao.load(__key);
            synchronized (this) {
                device = deviceNew;
            	device__resolvedKey = __key;
            }
        }
        return device;
    }

    public void setDevice(Device device) {
        synchronized (this) {
            this.device = device;
            deviceId = device == null ? null : device.getId();
            device__resolvedKey = deviceId;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Attribute> getAttributes() {
        if (attributes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AttributeDao targetDao = daoSession.getAttributeDao();
            List<Attribute> attributesNew = targetDao._querySceneDevice_Attributes(id, attributableType);
            synchronized (this) {
                if(attributes == null) {
                    attributes = attributesNew;
                }
            }
        }
        return attributes;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetAttributes() {
        attributes = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here

    public void copyAttributes(final List<Attribute> attributes) {
        BlinkApp.getDaoSession().runInTx(new Runnable() {
            @Override
            public void run() {
                AttributeDao attributeDao = BlinkApp.getDaoSession().getAttributeDao();
                for (Attribute attribute : attributes) {
                    Attribute attr = new Attribute();
                    attr.setAttributableType(ATTRIBUTABLE_TYPE);
                    attr.setAttributableId(getId());
                    attr.setAttributeType(attribute.getAttributeType());
                    attr.setValue(attribute.getValue());
                    attributeDao.insert(attr);
                    getAttributes().add(attr);
                }
                resetAttributes();
            }
        });
    }

    public void setLevel(final int level) {
        Attribute attribute = getAttributes().get(1);
        attribute.setValue(String.valueOf(level));
        attribute.update();
    }

    public void setOn(final boolean on) {
        Attribute attribute = getAttributes().get(0);
        attribute.setValue(on ? Attribute.ON : Attribute.OFF);
        attribute.update();
    }

    public boolean isOn() {
        return getAttributes().get(0).getBool();
    }

    public int getLevel() {
        return getAttributes().get(1).getInt();
    }

    public static SceneDevice newInstance() {
        SceneDevice sceneDevice = new SceneDevice();
        sceneDevice.setAttributableType(ATTRIBUTABLE_TYPE);
        return sceneDevice;
    }

    public void deleteWithReferences() {
        for (Attribute attribute : getAttributes()) {
            attribute.delete();
        }
        delete();
    }
    // KEEP METHODS END

}
