package com.avairebot.orion.database.transformers;

import com.avairebot.orion.Orion;
import com.avairebot.orion.contracts.database.transformers.Transformer;
import com.avairebot.orion.database.collection.DataRow;

public class GuildTypeTransformer extends Transformer {

    private String name = "Default";
    private GuildTypeLimits limits = new GuildTypeLimits();

    GuildTypeTransformer(DataRow data) {
        super(data);

        if (hasData()) {
            if (data.getString("type_name", null) != null) {
                name = data.getString("type_name");
            }

            if (data.getString("type_limits", null) != null) {
                GuildTypeLimits typeLimits = Orion.GSON.fromJson(data.getString("type_limits"), GuildTypeLimits.class);
                if (typeLimits != null) {
                    limits = typeLimits;
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public GuildTypeLimits getLimits() {
        return limits;
    }

    public class GuildTypeLimits {
        private GuildTypePlaylist playlist = new GuildTypePlaylist();
        private int aliases = 20;

        public int getAliases() {
            return aliases;
        }

        public GuildTypePlaylist getPlaylist() {
            return playlist;
        }

        public class GuildTypePlaylist {
            private int lists = 5;
            private int songs = 30;

            public int getPlaylists() {
                return lists;
            }

            public int getSongs() {
                return songs;
            }
        }
    }
}
