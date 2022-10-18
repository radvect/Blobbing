import java.util.*;

public class game {
    public static void main(String[] args){
     Blobservation blobs =  new Blobservation(8);
        List<Map<String,Integer>> generation0 = Arrays.asList(
                new HashMap<String,Integer>() {{ put("x", 0); put("y", 4); put("size", 3); }},
                new HashMap<String,Integer>() {{ put("x", 0); put("y", 7); put("size", 5); }},
                new HashMap<String,Integer>() {{ put("x", 2); put("y", 0); put("size", 2); }},
                new HashMap<String,Integer>() {{ put("x", 3); put("y", 7); put("size", 2); }},
                new HashMap<String,Integer>() {{ put("x", 4); put("y", 3); put("size", 4); }},
                new HashMap<String,Integer>() {{ put("x", 5); put("y", 6); put("size", 2); }},
                new HashMap<String,Integer>() {{ put("x", 6); put("y", 7); put("size", 1); }},
                new HashMap<String,Integer>() {{ put("x", 7); put("y", 0); put("size", 3); }},
                new HashMap<String,Integer>() {{ put("x", 7); put("y", 2); put("size", 1); }}
        );
        blobs.populate(generation0);
        blobs.print_state();
        blobs.move(1000   );
        System.out.println();
        blobs.print_state();
        System.out.println(12);

    }
}


class Blobservation {

    int h;
    int w;
    int field[][];
    List<Map<String,Integer>> blobs;
    int minimal_size = 500;
    int smallest;

    Blobservation(int i){
        this.h  = i;
        this.w = i;
        field = new int[h][w];
        for(int k = 0; k< h; k++){
            for(int j = 0; j< w; j++){
                field[k][j] = 0 ;
            }

        }
    }
    Blobservation(int h, int w){
        this.h = h;
        this.w = w;
        field = new int[h][w];
        for(int i = 0; i< h; i++){
            for(int j = 0; j< w; j++){
                field[i][j] = 0 ;

            }

        }
    }


    public void populate(List<Map<String,Integer>> mapList){

        for(int j = 0; j< h; j++){
            for(int i = 0; i< w; i++){
                field[j][i] = 0;

            }

        }
        List<Map<String,Integer>> blobs = new ArrayList<>(mapList);

        copy(mapList,blobs);
        this.blobs = blobs;
        this.smallest = blobs.get(0).get("size");

        for(int i = 0; i< blobs.size(); i++){
            int x_coor = blobs.get(i).get("x");
            int y_coor = blobs.get(i).get("y");
            int size = blobs.get(i).get("size");
            if(size==0){

                continue;
            }
            if(size<this.smallest){
                this.smallest = size;

            }
            if(x_coor == 1016){
                System.out.println(size);


            }


            field[y_coor][x_coor] += size;


            if(field[y_coor][x_coor]>20){
                System.out.println("Error");
                break;
            }

        }
    }

    public void print_state(){
        for(int i = 0; i< w; i++){
            for(int j = 0; j<h; j++){
                if(field[j][i]!=0){
                System.out.print(i   + " " + j + " " + field[j][i]);
                System.out.println();
            }}

            }

        }
        public void move(){
            List<Map<String,Integer>> map_temp = new ArrayList<>(blobs);
            copy(map_temp,blobs);

            for(int i = 0; i< blobs.size(); i++){


                int x_curr = blobs.get(i).get("x");
                int y_curr = blobs.get(i).get("y");
                int size_curr = blobs.get(i).get("size");
                if(size_curr==smallest){
                    continue;
                }
                if(size_curr == 0){
                    continue;
                }


                int closest = h+w;
                int priority_direction=500;
                int priority=500;
                int size_way = 0;


                for(int j = 0; j< blobs.size(); j++){
                    int x1=blobs.get(j).get("x");
                    int y1=blobs.get(j).get("y");
                    int size1 = blobs.get(j).get("size");
                    if(size1==0){
                        continue;

                    }

                    if(i==j){
                        continue;
                    }

                    if(size1<size_curr){
                        int distance = Math.max(Math.abs(x1-x_curr) ,Math.abs(y1-y_curr)) ;
                        //System.out.println("dd "+i + " " + x1 + " " + y1 + " " +distance);
                        if((distance<closest)){
                            size_way = size1;
                            //System.out.println(i + " sdsd");
                            closest = distance;
                            priority_direction = direction_choosing(x1,y1,x_curr,y_curr);
                            priority = priority_direction;}
                        else if((distance==closest)){
                            //System.out.println(x_curr + " " + y_curr);
                            //System.out.println(x1 + " "+  y1);
                            //System.out.println(size1);
                            //System.out.println(size_way);
                            if(size1>size_way){
                                //System.out.println(1);
                                size_way = size1;
                                priority_direction = direction_choosing(x1,y1,x_curr,y_curr);
                                priority = priority_direction;
                            }
                            else if(size1==size_way){
                                //System.out.println(2);
                                priority_direction = direction_choosing(x1,y1,x_curr,y_curr);
                                if(priority_direction<priority){
                                    priority = priority_direction;
                                }
                                else{
                                    //System.out.println(3);
                                    continue;
                                }
                            }
                            else if(size1<size_way){
                                continue;
                            }

                        }
                        else if(distance>closest){
                            continue;
                        }

                    }




                }
                //if(blobs.get(i).get("size")==5){
                    //System.out.println("dir"  + i +" " + " " + priority);
                    //System.out.println("coors " + x1 + " " +y1  + " " + size1);

                //}


                if(priority == 0){
                    map_temp.get(i).put("x",x_curr-1 );

                }
                else if(priority == 1){
                    map_temp.get(i).put("y",y_curr-1 );
                    map_temp.get(i).put("x",x_curr+1 );

                }
                else if(priority == 2){
                    map_temp.get(i).put("y",y_curr+1 );

                }
                else if(priority== 3){
                    map_temp.get(i).put("x",x_curr+1 );
                    map_temp.get(i).put("y",y_curr+1 );

                }
                else if(priority == 4){
                    map_temp.get(i).put("x", x_curr+1);
                }
                else if(priority == 5){
                    map_temp.get(i).put("y",y_curr+1 );
                    map_temp.get(i).put("x",x_curr-1 );

                }
                else if(priority == 6){
                    map_temp.get(i).put("y",y_curr-1 );

                }
                else if(priority == 7){
                    map_temp.get(i).put("y",y_curr-1 );
                    map_temp.get(i).put("x",x_curr-1 );

                }

            }

            for(int i = 0; i<map_temp.size();i++) {
                int x_curr = map_temp.get(i).get("x");
                int y_curr = map_temp.get(i).get("y");
                int size_curr = map_temp.get(i).get("size");
                for (int j = 0; j < map_temp.size(); j++) {
                    int x1 = map_temp.get(j).get("x");
                    int y1 = map_temp.get(j).get("y");
                    int size1 = map_temp.get(j).get("size");
                    if (i == j) {
                        continue;
                    }
                    if(size1 == 0){
                        continue;
                    }
                    if ((x_curr == x1) & (y_curr == y1)) {
                        size_curr = size_curr+size1;
                        map_temp.get(i).put("size", size_curr);
                        map_temp.get(j).put("size", 0);
                        map_temp.get(j).put("x", h+w+1000);
                        map_temp.get(j).put("y", h+w+1000);
                    }


                }

            }

            List<Map<String,Integer>> blobs = new ArrayList<>(map_temp);
            copy(map_temp,blobs);

            populate(blobs);
            minimal_size = 500;


            }











    public void move(int step){
        for(int i = 0; i<step; i++ ){
            move();
        }
    }
    public int direction_choosing(int x1, int y1, int x_curr, int y_curr){
        int dir = 0;
        if(x1 == x_curr){
            if(y1>y_curr){
                dir = 2;
            }
            else{
                dir = 6;
            }
        }
        if(y1 == y_curr){
            if(x1>x_curr){
                dir = 4;
            }
            else{
                dir = 0;
            }
        }
        if(y1>y_curr & x1>x_curr){
            dir = 3;
        }
        if(y1<y_curr & x1>x_curr){
            dir= 1;
        }
        if(y1>y_curr & x1<x_curr){
            dir = 5;
        }
        if(y1<y_curr & x1<x_curr) {
            dir = 7;
        }


        return dir;
    }
    public void copy(List<Map<String,Integer>> original, List<Map<String,Integer>> copy){

        for (int i = 0; i<original.size(); i++) {
            Map<String, Integer> new_map = new HashMap<String, Integer>();
            new_map.putAll(original.get(i));
            copy.set(i, new_map);
        }
    }
}




