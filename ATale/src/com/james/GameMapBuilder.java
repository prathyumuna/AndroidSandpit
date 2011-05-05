package com.james;

import static com.james.GameState.EMPTY_THING;
import static com.james.GameState.WALL_THING;

import com.james.thing.Avatar;
import com.james.thing.Thing;

public class GameMapBuilder {
    private final MonsterBuilder monsterBuilder = new MonsterBuilder();

    public GameMap build(Avatar avatar, String buffer) {
        String rows[] = buffer.split("\n");

        int height = rows.length;

        Thing[][] map = new Thing[height][];
        for (int y = 0; y < height; y++) {
            int width = rows[y].length();

            map[y] = new Thing[width];
            for (int x = 0; x < width; x++) {
                map[y][x] = buildThing(avatar, width, height, rows[y].charAt(x), x, y);
            }
        }
        return new GameMap(map);
    }

    private Thing buildThing(Avatar avatar, int width, int height, char val, int x, int y) {
        switch (val) {
            case '0':
                return EMPTY_THING;
            case '+':
                return WALL_THING;
            case '7':
                return this.monsterBuilder.build(val);
            case '*':
                avatar.set(x, y);
                return avatar;
            default:
                throw new RuntimeException("Unknown data value: " + val);
        }
    }
}
