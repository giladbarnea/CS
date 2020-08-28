/**
 * This program creates an FAT12 image
 * @author Amit Abecasis
 * @id 205480072
 * @date 28/12/2018
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <fcntl.h>
#include <unistd.h>

#include "fat12.h"

int fid; /* global variable set by the open() function */

//Write content to sector
int fd_write(int sector_number, char *buffer) {
    int dest, len;
    dest = lseek(fid, sector_number * DEFAULT_SECTOR_SIZE, SEEK_SET);
    if (dest != sector_number * DEFAULT_SECTOR_SIZE) {
        fprintf(stderr, "Error in seeking to writing point.\n");
    }
    len = write(fid, buffer, DEFAULT_SECTOR_SIZE);
    if (len != DEFAULT_SECTOR_SIZE) {
        fprintf(stderr, "Error in writing to sector.\n");
    }
    return len;
}

int main(int argc, char *argv[]) {
    char content[DEFAULT_SECTOR_SIZE];  //Buffer used to write to sector
    boot_record_t boot;                 //Boot sector variable
    int sector = 0;                     //Sector writer index

    //Argument check
    if (argc != 2) {
        printf("Usage: %s <floppy_image>\n", argv[0]);
        exit(1);
    }

    //Opening/Creating file
    if ((fid = open(argv[1], O_RDWR | O_CREAT, 0644)) < 0) {
        perror("Error: ");
        return -1;
    }

    //Setting boot sector values
    char jmp[] = BOOTJMP;
    memcpy(boot.bootjmp, jmp, sizeof(jmp));
    memcpy(boot.oem_id, OEMID, sizeof(OEMID));
    boot.sector_size = SECTOR_SIZE;
    boot.sectors_per_cluster = SECTORS_PER_CLUSTER;
    boot.reserved_sector_count = RESERVED_SECTOR_COUNT;
    boot.number_of_fats = NUMBER_OF_FATS;
    boot.number_of_dirents = NUMBER_OF_DIRENTS;
    boot.sector_count = SECTOR_COUNT;
    boot.media_type = MEDIA_TYPE;
    boot.fat_size_sectors = FAT_SIZE_SECTORS;
    boot.sectors_per_track = SECTORS_PER_TRACK;
    boot.nheads = NHEADS;
    boot.sectors_hidden = SECTORS_HIDDEN;
    boot.sector_count_large = SECTOR_COUNT_LARGE;

    //Writing boot sector to buffer and buffer to image
    memcpy(content, &boot, sizeof(boot));

    if (fd_write(sector++, content) != sizeof(content)) {
        fprintf(stderr, "Error writing boot sector.\n");
        close(fid);
        return -1;
    }

    //Zeroing the buffer
    memset(content, 0, sizeof(content));

    //Writing FAT1 and FAT2 to disk
    for (int fatTable = 0; fatTable < boot.number_of_fats; fatTable++)
        for (int fatSector = 0; fatSector < boot.fat_size_sectors; fatSector++) {
            if (!fatSector)
                ((uint32_t *) content)[0] = RESERVED_CLUSTER_VALUE; //First sector in each table contains reserved value
            else
                ((uint32_t *) content)[0] = 0;

            if (fd_write(sector, content) != sizeof(content)) {
                fprintf(stderr, "Error writing to fat table #%d in fatSector %d in sector %d.\n", fatTable, fatSector,
                        sector);
                close(fid);
                return -1;
            }
            sector++;
        }

    //Writing root directory
    for (int rootSector = 0; rootSector < ROOT_SECTORS; rootSector++) {
        if (fd_write(sector, content) != sizeof(content)) {
            fprintf(stderr, "Error writing to root directory in root sector #%d in sector %d.\n", rootSector, sector);
            close(fid);
            return -1;
        }
        sector++;
    }

    //Writing data area
    content[0] = FREE_DIRECTORY_VALUE;  //Initializes each sector as available to write to
    for (; sector < boot.sector_count; sector++)
        if (fd_write(sector, content) != sizeof(content)) {
            fprintf(stderr, "Error writing to data area in data sector in sector %d.\n", sector);
            close(fid);
            return -1;
        }

    //Closing file
    close(fid);
    return 0;
}