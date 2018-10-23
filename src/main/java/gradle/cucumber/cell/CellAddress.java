package gradle.cucumber.cell;

import gradle.cucumber.bomb.Bomb;
import gradle.cucumber.superpower.Superpower;
import gradle.cucumber.wall.Wall;

public class CellAddress implements Cell {


    private Integer axisX;
    private Integer axisY;
    private Wall wall;
    private boolean enemy;
    private boolean bomb;
    private boolean bagula;
    private Cell northCell;
    private Cell southCell;
    private Cell eastCell;
    private Cell westCell;
    private Superpower superP;


    public CellAddress(Integer anAxisX, Integer anAxisY, Wall wall, boolean enemy, Cell nc, Cell sc, Cell ec, Cell wc) {
        this.axisX = anAxisX;
        this.axisY = anAxisY;
        this.wall = wall;
        this.enemy = enemy;
        this.bagula = false;
        this.bomb= false;
        this.northCell=nc;
        this.southCell=sc;
        this.eastCell=ec;
        this.westCell=wc;


    }


    @Override
    public Wall getWall(){
        return wall;
    }
    @Override
    public boolean hasAnEnemy() {
        return enemy;
    }
    @Override
    public boolean hasABoss() {
        return bagula;
    }

    @Override
    public void setBomb(Bomb bomb) throws InterruptedException {
        Thread.sleep(bomb.getTicks()*1000);
        bomb.exploit(this);
    }

    @Override
    public void breakWall(int ratio) {
        wall.breakWall();
        if(ratio > 0){
            int newRatio = ratio - 1;
            northCell.breakWall(newRatio);
            westCell.breakWall(newRatio);
            eastCell.breakWall(newRatio);
            southCell.breakWall(newRatio);
        }

    }

    @Override
    public void addEnemy() {
        enemy = true;
    }

    @Override
    public void addBoss() {
        bagula = true;
    }


    @Override
    public void killEnemy(int ratio) {
        enemy = false;
        if(ratio > 0){
            int newRatio = ratio - 1;
            northCell.killEnemy(newRatio);
            westCell.killEnemy(newRatio);
            eastCell.killEnemy(newRatio);
            southCell.killEnemy(newRatio);
        }
    }

    @Override
    public void killBoss(int ratio) {
        bagula = false;
        superP = new Superpower();
        if(ratio > 0){
            int newRatio = ratio - 1;
            northCell.killBoss(newRatio);
            westCell.killBoss(newRatio);
            eastCell.killBoss(newRatio);
            southCell.killBoss(newRatio);
        }
    }

    @Override
    public void addWall(Wall wall1) {
        wall = wall1;
    }

    @Override
    public boolean hasASuperPower() {
        return this.superP != null;
    }

    @Override
    public void addSuperPower(Superpower sp) {
        this.superP = sp;
    }

    @Override
    public Cell getEast() {
        return this.eastCell;
    }

    @Override
    public Cell getNorth() {
        return this.northCell;
    }

    @Override
    public Cell getWest() {
        return this.westCell;
    }

    @Override
    public Cell getSouth() {
        return this.southCell;
    }

    @Override
    public Superpower getSuperPower() {
        return superP;
    }

    @Override
    public Integer getX() {
        return axisX;
    }

    @Override
    public Integer getY() {
        return axisY;
    }


}
