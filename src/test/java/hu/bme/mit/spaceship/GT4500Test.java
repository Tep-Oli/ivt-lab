package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore mockPTS;
  TorpedoStore mockSTS;

  @BeforeEach
  public void init(){
    mockPTS = mock(TorpedoStore.class);
    mockSTS = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPTS, mockSTS);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(0)).isEmpty();
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    when(mockPTS.isEmpty()).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false).thenReturn(true);
    when(mockSTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(4)).isEmpty();

    verify(mockSTS,times(1)).fire(1);
    verify(mockSTS,times(2)).isEmpty();
  }

  @Test
  public void singlefire_if_PNotEmpty_SNotEmpty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(0)).isEmpty();

  }


  @Test
  public void singlefire_if_PNotEmpty_SEmpty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(0)).isEmpty();
  }

  @Test
  public void singlefire_if_PEmpty_SNotEmpty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(0)).fire(1);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(1)).fire(1);
    verify(mockSTS,times(1)).isEmpty();

  }
  @Test
  public void singlefire_TwoTimes_if_PNotEmpty_SNotEmpty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(0)).isEmpty();

    // Act SecondTime
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result2);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(1)).fire(1);
    verify(mockSTS,times(1)).isEmpty();
  }

  @Test
  public void singlefire_TwoTimes_if_PNotEmpty_SEmpty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(0)).isEmpty();

    // Act SecondTime
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result2);
    verify(mockPTS,times(2)).fire(1);
    verify(mockPTS,times(2)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_All_Success_SEmpty(){
    // Arrange

    when(mockPTS.isEmpty()).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(2)).fire(1);
    verify(mockPTS,times(5)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(2)).isEmpty();
  }


  @Test
  public void fireTorpedo_All_Success_PEmpty(){
    // Arrange

    when(mockSTS.isEmpty()).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true);
    when(mockSTS.fire(1)).thenReturn(true);
    when(mockPTS.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(0)).fire(1);
    verify(mockPTS,times(5)).isEmpty();

    verify(mockSTS,times(2)).fire(1);
    verify(mockSTS,times(5)).isEmpty();
 
  }

  @Test
  public void firedefault(){
    // Arrange
    // Act0
    boolean result = ship.fireTorpedo(FiringMode.UNKNOWN);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Twice_SuccessFirst_FailSecond(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(0)).isEmpty();
  }

  @Test
  public void fireTorpedo_Single_Fails(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPTS,times(1)).isEmpty();

    verify(mockSTS,times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_Single_SuccesFirst_ThenFails(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false).thenReturn(true);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).isEmpty();
    verify(mockPTS,times(1)).fire(1);

    verify(mockSTS,times(0)).isEmpty();

    // Act
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result2);
    verify(mockPTS,times(2)).isEmpty();
    verify(mockPTS,times(1)).fire(1);

    verify(mockSTS,times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_All_Fails(){
    // Arrange

    when(mockSTS.isEmpty()).thenReturn(false).thenReturn(true);
    when(mockPTS.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockPTS,times(0)).fire(1);
    verify(mockPTS,times(3)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(3)).isEmpty();
 
  }

  @Test
  public void fireTorpedo_All_Succes_branch(){
    // Arrange

    when(mockPTS.isEmpty()).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPTS,times(1)).fire(1);
    verify(mockPTS,times(5)).isEmpty();

    verify(mockSTS,times(0)).fire(1);
    verify(mockSTS,times(2)).isEmpty();
 
  }
}
