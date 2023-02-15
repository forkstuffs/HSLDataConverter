package me.william278.husksync.bukkit.data;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

/**
 * Holds serialized data storage methods used in HuskSync v1.x
 */
public class DataSerializer {

    /**
         * A record used to store data for advancement synchronisation
         *
         * @deprecated Old format - Use {@link AdvancementRecordDate} instead
         */
        @Deprecated
        @SuppressWarnings("DeprecatedIsStillUsed")
        // Suppress deprecation warnings here (still used for backwards compatibility)
        public static final class AdvancementRecord implements Serializable {
        private final String advancementKey;
        private final ArrayList<String> awardedAdvancementCriteria;

        /**
         *
         */
        public AdvancementRecord(String advancementKey,
                                 ArrayList<String> awardedAdvancementCriteria) {
            this.advancementKey = advancementKey;
            this.awardedAdvancementCriteria = awardedAdvancementCriteria;
        }

        public String advancementKey() {
            return advancementKey;
        }

        public ArrayList<String> awardedAdvancementCriteria() {
            return awardedAdvancementCriteria;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (AdvancementRecord) obj;
            return Objects.equals(this.advancementKey, that.advancementKey) &&
                   Objects.equals(this.awardedAdvancementCriteria, that.awardedAdvancementCriteria);
        }

        @Override
        public int hashCode() {
            return Objects.hash(advancementKey, awardedAdvancementCriteria);
        }

        @Override
        public String toString() {
            return "AdvancementRecord[" +
                   "advancementKey=" + advancementKey + ", " +
                   "awardedAdvancementCriteria=" + awardedAdvancementCriteria + ']';
        }

        }

    /**
         * A record used to store data for a player's statistics
         */
        public static final class StatisticData implements Serializable {
        private final HashMap<Statistic, Integer> untypedStatisticValues;
        private final HashMap<Statistic, HashMap<Material, Integer>> blockStatisticValues;
        private final HashMap<Statistic, HashMap<Material, Integer>> itemStatisticValues;
        private final HashMap<Statistic, HashMap<EntityType, Integer>> entityStatisticValues;

        /**
         *
         */
        public StatisticData(HashMap<Statistic, Integer> untypedStatisticValues,
                             HashMap<Statistic, HashMap<Material, Integer>> blockStatisticValues,
                             HashMap<Statistic, HashMap<Material, Integer>> itemStatisticValues,
                             HashMap<Statistic, HashMap<EntityType, Integer>> entityStatisticValues) {
            this.untypedStatisticValues = untypedStatisticValues;
            this.blockStatisticValues = blockStatisticValues;
            this.itemStatisticValues = itemStatisticValues;
            this.entityStatisticValues = entityStatisticValues;
        }

        public HashMap<Statistic, Integer> untypedStatisticValues() {
            return untypedStatisticValues;
        }

        public HashMap<Statistic, HashMap<Material, Integer>> blockStatisticValues() {
            return blockStatisticValues;
        }

        public HashMap<Statistic, HashMap<Material, Integer>> itemStatisticValues() {
            return itemStatisticValues;
        }

        public HashMap<Statistic, HashMap<EntityType, Integer>> entityStatisticValues() {
            return entityStatisticValues;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (StatisticData) obj;
            return Objects.equals(this.untypedStatisticValues, that.untypedStatisticValues) &&
                   Objects.equals(this.blockStatisticValues, that.blockStatisticValues) &&
                   Objects.equals(this.itemStatisticValues, that.itemStatisticValues) &&
                   Objects.equals(this.entityStatisticValues, that.entityStatisticValues);
        }

        @Override
        public int hashCode() {
            return Objects.hash(untypedStatisticValues, blockStatisticValues, itemStatisticValues, entityStatisticValues);
        }

        @Override
        public String toString() {
            return "StatisticData[" +
                   "untypedStatisticValues=" + untypedStatisticValues + ", " +
                   "blockStatisticValues=" + blockStatisticValues + ", " +
                   "itemStatisticValues=" + itemStatisticValues + ", " +
                   "entityStatisticValues=" + entityStatisticValues + ']';
        }

        }

    /**
         * A record used to store data for native advancement synchronisation, tracking advancement date progress
         */
        public static final class AdvancementRecordDate implements Serializable {
        private final String key;
        private final Map<String, Date> criteriaMap;

        /**
         *
         */
        public AdvancementRecordDate(String key, Map<String, Date> criteriaMap) {
            this.key = key;
            this.criteriaMap = criteriaMap;
        }

        public AdvancementRecordDate(String key, List<String> criteriaList) {
                this(key, new HashMap<>() {{
                    criteriaList.forEach(s -> put(s, Date.from(Instant.EPOCH)));
                }});
            }

        public String key() {
            return key;
        }

        public Map<String, Date> criteriaMap() {
            return criteriaMap;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (AdvancementRecordDate) obj;
            return Objects.equals(this.key, that.key) &&
                   Objects.equals(this.criteriaMap, that.criteriaMap);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, criteriaMap);
        }

        @Override
        public String toString() {
            return "AdvancementRecordDate[" +
                   "key=" + key + ", " +
                   "criteriaMap=" + criteriaMap + ']';
        }

        }

    /**
         * A record used to store data for a player's location
         */
        public static final class PlayerLocation implements Serializable {
        private final double x;
        private final double y;
        private final double z;
        private final float yaw;
        private final float pitch;
        private final String worldName;
        private final World.Environment environment;

        /**
         *
         */
        public PlayerLocation(double x, double y, double z, float yaw, float pitch,
                              String worldName, World.Environment environment) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
            this.worldName = worldName;
            this.environment = environment;
        }

        public double x() {
            return x;
        }

        public double y() {
            return y;
        }

        public double z() {
            return z;
        }

        public float yaw() {
            return yaw;
        }

        public float pitch() {
            return pitch;
        }

        public String worldName() {
            return worldName;
        }

        public World.Environment environment() {
            return environment;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (PlayerLocation) obj;
            return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x) &&
                   Double.doubleToLongBits(this.y) == Double.doubleToLongBits(that.y) &&
                   Double.doubleToLongBits(this.z) == Double.doubleToLongBits(that.z) &&
                   Float.floatToIntBits(this.yaw) == Float.floatToIntBits(that.yaw) &&
                   Float.floatToIntBits(this.pitch) == Float.floatToIntBits(that.pitch) &&
                   Objects.equals(this.worldName, that.worldName) &&
                   Objects.equals(this.environment, that.environment);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, yaw, pitch, worldName, environment);
        }

        @Override
        public String toString() {
            return "PlayerLocation[" +
                   "x=" + x + ", " +
                   "y=" + y + ", " +
                   "z=" + z + ", " +
                   "yaw=" + yaw + ", " +
                   "pitch=" + pitch + ", " +
                   "worldName=" + worldName + ", " +
                   "environment=" + environment + ']';
        }

        }

}
