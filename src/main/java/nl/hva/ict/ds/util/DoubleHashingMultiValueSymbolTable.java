package nl.hva.ict.ds.util;

import java.util.ArrayList;
import nl.hva.ict.ds.Player;

import java.util.List;

public class DoubleHashingMultiValueSymbolTable implements MultiValueSymbolTable<String, Player> {

    private Player[] playerList;
    private int ARRAY_SIZE = 0;
    private int PRIME = 5;
    private int collisions = 0;

    public DoubleHashingMultiValueSymbolTable(int arraySize) {
        this.ARRAY_SIZE = arraySize;
        playerList = new Player[arraySize];
    }

    @Override
    public void put(String key, Player value) {
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);

        while (playerList[hashVal] != null) {
            collisions++;
            hashVal += stepSize;
            hashVal %= ARRAY_SIZE;
        }
        playerList[hashVal] = value;
    }

    private int hashFunc1(String key) {
        return (key.hashCode() & 0x7fffffff) % ARRAY_SIZE;
    }

    private int hashFunc2(String key)
    {
        return (PRIME - (key.hashCode() % PRIME));
    }

    @Override
    public List<Player> get(String key) {
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);
        List<Player> players = new ArrayList<>();
        while (playerList[hashVal] != null) {
            Player currentPlayer = playerList[hashVal];
            String fullname = currentPlayer.getFirstName() + currentPlayer.getLastName();
            if (fullname.equals(key)) {
                players.add(playerList[hashVal]);
            }
            hashVal += stepSize;
            hashVal %= ARRAY_SIZE;
        }
        return players;
    }

    @Override
    public int getCollisions(){
        return this.collisions;
    }
}
