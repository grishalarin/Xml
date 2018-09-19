public interface Strategy {


        public void writeObj(OutputFileProcessor outputProcessor, Object obj);


        public Object readObj(InputFileProcessor inputProcessor);


}
