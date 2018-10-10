package dictionary.hh;

public class Word {
        public String target;
        public String explain;

        public Word(){

        }

        public Word(String target, String explain){
            this.target = target;
            this.explain = explain;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

}
