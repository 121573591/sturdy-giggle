package cn.hutool.poi.excel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

public class ExcelPicUtil {
  public static Map<String, PictureData> getPicMap(Workbook workbook, int sheetIndex) {
    Assert.notNull(workbook, "Workbook must be not null !", new Object[0]);
    if (sheetIndex < 0)
      sheetIndex = 0; 
    if (workbook instanceof HSSFWorkbook)
      return getPicMapXls((HSSFWorkbook)workbook, sheetIndex); 
    if (workbook instanceof XSSFWorkbook)
      return getPicMapXlsx((XSSFWorkbook)workbook, sheetIndex); 
    throw new IllegalArgumentException(StrUtil.format("Workbook type [{}] is not supported!", new Object[] { workbook.getClass() }));
  }
  
  private static Map<String, PictureData> getPicMapXls(HSSFWorkbook workbook, int sheetIndex) {
    Map<String, PictureData> picMap = new HashMap<>();
    List<HSSFPictureData> pictures = workbook.getAllPictures();
    if (CollectionUtil.isNotEmpty(pictures)) {
      HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
      for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
        if (shape instanceof HSSFPicture) {
          int pictureIndex = ((HSSFPicture)shape).getPictureIndex() - 1;
          HSSFClientAnchor anchor = (HSSFClientAnchor)shape.getAnchor();
          picMap.put(StrUtil.format("{}_{}", new Object[] { Integer.valueOf(anchor.getRow1()), Short.valueOf(anchor.getCol1()) }), (PictureData)pictures.get(pictureIndex));
        } 
      } 
    } 
    return picMap;
  }
  
  private static Map<String, PictureData> getPicMapXlsx(XSSFWorkbook workbook, int sheetIndex) {
    Map<String, PictureData> sheetIndexPicMap = new LinkedHashMap<>();
    XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
    for (POIXMLDocumentPart dr : sheet.getRelations()) {
      if (dr instanceof XSSFDrawing) {
        XSSFDrawing drawing = (XSSFDrawing)dr;
        List<XSSFShape> shapes = drawing.getShapes();
        for (XSSFShape shape : shapes) {
          if (shape instanceof XSSFPicture) {
            XSSFPicture pic = (XSSFPicture)shape;
            CTMarker ctMarker = pic.getPreferredSize().getFrom();
            sheetIndexPicMap.put(StrUtil.format("{}_{}", new Object[] { Integer.valueOf(ctMarker.getRow()), Integer.valueOf(ctMarker.getCol()) }), pic.getPictureData());
          } 
        } 
      } 
    } 
    return sheetIndexPicMap;
  }
}
