package us.flower.dayary.service.community.image;

import org.springframework.stereotype.Service;

@Service
public class CommunityImageServiceImpl implements CommunityImageService {

/*    private final Path rootLocation;

    @Value("${communityImagePath}")
    private String uploadPath;


    public CommunityImageServiceImpl(){
        this.rootLocation = Paths.get(uploadPath);
    }

    @Autowired
    CommunityBoardFileRepository fileRepository;

    @Override
    public Stream<Long> loadAll() {
        List<UploadFile> files = fileRepository.findAll();
        return files.stream().map(file -> file.getId());
    }

    @Override
    public UploadFile load(long fileId) {
        return fileRepository.findOne(fileId);
    }

    @Override
    public Resource loadAsResource(String fileName) throws Exception {
        try{
            if(fileName.toCharArray()[0]=='/'){
                fileName = fileName.substring(1);
            }

            Path file = loadPath(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            } else {
                throw new Exception("Could not read file: " + fileName);
            }
        }catch(Exception e){
            throw new Exception("Could not read file:" + fileName);
        }
    }

    @Override
    public Path loadPath(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public UploadFile store(MultipartFile file) throws Exception {
        try{
            if(file.isEmpty()){
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }

            String saveFileName = FileManager.fileSave(rootLocation.toString(), file);

            if(saveFileName.toCharArray()[0] == '/'){
                saveFileName = saveFileName.substring(1);
            }

            Resource resource = loadAsResource(saveFileName);

            UploadFile saveFile = new UploadFile();
            saveFile.setSaveFileName(saveFileName);
            saveFile.setFileName(file.getOriginalFilename());
            saveFile.setContentType(file.getContentType());
            saveFile.setFilePath(rootLocation.toString() + File.separator + saveFileName);
            saveFile.setSize(resource.contentLength());
            saveFile = fileRepository.save(saveFile);

            return saveFile;
        }catch(IOException e){
            throw new Exception("Failed to store file" + file.getOriginalFilename(), e);
        }
    }*/
}
